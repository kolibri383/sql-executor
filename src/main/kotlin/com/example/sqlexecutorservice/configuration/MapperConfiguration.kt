package com.example.sqlexecutorservice.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MapperConfiguration {
    @Bean("objectMapper")
    fun getMapper() = ObjectMapper()
}