package com.example.sqlexecutorservice.configuration

import com.example.sqlexecutorservice.dto.ExecutorAnswerDto
import com.example.sqlexecutorservice.serializer.ExecutorAnswerDtoSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootStrapServers: String? = null

    fun producerConfig(): Map<String, Any> {
        val props = HashMap<String, Any>().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers!!)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ExecutorAnswerDtoSerializer::class.java)
        }
        return props
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, ExecutorAnswerDto> =
        DefaultKafkaProducerFactory(producerConfig())

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, ExecutorAnswerDto>) =
        KafkaTemplate(producerFactory)
}