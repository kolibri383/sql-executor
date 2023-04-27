package com.example.sqlexecutorservice.service

import com.example.sqlexecutorservice.dao.SqlDao
import com.example.sqlexecutorservice.dto.EventValidationTaskDto
import com.example.sqlexecutorservice.dto.ExecutorAnswerDto
import com.example.testsqlexecuter.dto.Status
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class KafkaListeners(
    private val sqlDao: SqlDao,
    @Autowired private val kafkaTemplate: KafkaTemplate<String, ExecutorAnswerDto>,
) {


    @KafkaListener(topics = ["sql-check-correct"], groupId = "groupId", containerFactory = "factory")
    fun listener(data: ConsumerRecord<String, EventValidationTaskDto>) {
        val task = data.value()
        checkSql(
            ExecutorAnswerDto(
                username = task.username,
                taskId = task.taskId,
                moduleId = task.moduleId,
                userSql = task.userSql,
                database = task.database,
            ), task.correctSql!!
        )
    }

    private fun checkSql(executorAnswerDto: ExecutorAnswerDto, correctSql: String) {
        if (isEqualsSql(executorAnswerDto.userSql!!, correctSql)) {
            executorAnswerDto.resultStatus = Status.BP_OK
        } else {
            try {
                executorAnswerDto.resultStatus =
                    getResultCode(sqlDao.selectExecute(executorAnswerDto.userSql!!), sqlDao.selectExecute(correctSql))
            } catch (e: RuntimeException) {
                executorAnswerDto.apply {
                    resultStatus = Status.BP_SQL_ERROR
                    messageException = e.message.toString()
                }
            }
        }
        sendKafkaMessage(executorAnswerDto)
    }


    private fun isEqualsSql(userSql: String, answerSql: String) =
        answerSql.uppercase(Locale.getDefault()) == userSql.uppercase(Locale.getDefault())


    private fun getResultCode(userAnswer: List<Any>, correctAnswer: List<Any>) =
        if (userAnswer == correctAnswer) Status.BP_OK else Status.BP_NOT


    private fun sendKafkaMessage(executorAnswerDto: ExecutorAnswerDto) {
        kafkaTemplate.send("sql-check-result", executorAnswerDto)
    }
}