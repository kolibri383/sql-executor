package com.example.sqlexecutorservice.configuration

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class KafkaTopicConfig {


    @Bean
    fun sqlResponseTopic(): NewTopic {
        return TopicBuilder.name("sql-check-result")
            .build()
    }
}