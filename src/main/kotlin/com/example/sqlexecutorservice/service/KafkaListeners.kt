package com.example.sqlexecutorservice.service

import com.example.sqlexecutorservice.dao.SqlDao
import com.example.sqlexecutorservice.dto.ExecutorAnswerDto
import com.example.sqlexecutorservice.dto.SqlTaskAnswerDto
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class KafkaListeners(@Autowired private val sqlDao: SqlDao,
                     @Autowired private val kafkaTemplate: KafkaTemplate<String, ExecutorAnswerDto>) {


    @KafkaListener(topics = ["sql-check-correct"], groupId = "groupId", containerFactory = "factory")
    fun listener(data: ConsumerRecord<String, SqlTaskAnswerDto>):Boolean{
        val sqlTaskAnswerDto = data.value()
        val executorAnswerDto = ExecutorAnswerDto().apply {
            userName = sqlTaskAnswerDto.userName
            taskId = sqlTaskAnswerDto.taskId
            categoryId = sqlTaskAnswerDto.categoryId
            userSql = sqlTaskAnswerDto.userSql
        }
        if(sqlTaskAnswerDto.correctSql?.uppercase()==sqlTaskAnswerDto.userSql?.uppercase()){
            executorAnswerDto.resultStatus = "bp_ok"
            kafkaTemplate.send("sql-result",executorAnswerDto)
            return true
        }
        try {
            val userAnswer = sqlDao.selectExecute(sqlTaskAnswerDto.userSql!!)
            if(sqlDao.selectExecute(sqlTaskAnswerDto.correctSql!!)==userAnswer)
                executorAnswerDto.resultStatus = "bp_ok"
            else
                executorAnswerDto.resultStatus = "bp_data_not_equals"
        }catch (ex: RuntimeException){
            executorAnswerDto.apply {
                resultStatus = "bp_error_sql"
                messageException = ex.message!!
            }
        }
        kafkaTemplate.send("sql-result",executorAnswerDto)
        println("Listener received: ${data.value()}")
        return true
    }
}