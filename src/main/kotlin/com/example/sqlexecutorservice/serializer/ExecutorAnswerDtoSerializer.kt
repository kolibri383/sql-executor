package com.example.sqlexecutorservice.serializer

import com.example.sqlexecutorservice.dto.ExecutorAnswerDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer

class ExecutorAnswerDtoSerializer : Serializer<ExecutorAnswerDto> {
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun serialize(topic: String?, data:ExecutorAnswerDto?): ByteArray? {
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Product to ByteArray[]")
        )
    }

    override fun close() {}
}