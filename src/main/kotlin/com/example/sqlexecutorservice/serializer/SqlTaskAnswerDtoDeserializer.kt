package com.example.sqlexecutorservice.serializer

import com.example.sqlexecutorservice.dto.SqlTaskAnswerDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer

class SqlTaskAnswerDtoDeserializer : Deserializer<SqlTaskAnswerDto> {
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun close() {}
    override fun deserialize(topic: String?, data: ByteArray?): SqlTaskAnswerDto {
        return objectMapper.readValue(String(data!!), SqlTaskAnswerDto::class.java)
    }
}