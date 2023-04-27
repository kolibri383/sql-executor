package com.example.sqlexecutorservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ExecutorConfiguration {
    @Bean
    fun restTemplate(): RestTemplate? {
        return RestTemplate()
    }
}