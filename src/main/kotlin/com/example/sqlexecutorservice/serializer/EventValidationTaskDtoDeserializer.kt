package com.example.sqlexecutorservice.serializer

import com.example.sqlexecutorservice.dto.EventValidationTaskDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer

class EventValidationTaskDtoDeserializer : Deserializer<EventValidationTaskDto> {
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun close() {}
    override fun deserialize(topic: String?, data: ByteArray?): EventValidationTaskDto {
        return objectMapper.readValue(String(data!!), EventValidationTaskDto::class.java)
    }
}