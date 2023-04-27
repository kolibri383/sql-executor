package com.example.sqlexecutorservice.configuration

import com.example.sqlexecutorservice.dto.EventValidationTaskDto
import com.example.sqlexecutorservice.serializer.EventValidationTaskDtoDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer


@Configuration
@EnableKafka
class KafkaConsumerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootStrapServers: String? = null

    fun consumerConfig(): Map<String, Any> {
        val props = HashMap<String, Any>().apply {
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers!!)
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EventValidationTaskDtoDeserializer::class.java)
        }
        return props
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, EventValidationTaskDto> =
        DefaultKafkaConsumerFactory(consumerConfig())

    @Bean
    fun factory(consumerFactory: ConsumerFactory<String, EventValidationTaskDto>):
            KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EventValidationTaskDto>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, EventValidationTaskDto>().apply {
            this.consumerFactory = consumerFactory
        }
        return factory
    }
}