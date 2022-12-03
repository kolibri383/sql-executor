package com.example.sqlexecutorservice.handler

import com.example.sqlexecutorservice.dto.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import java.sql.SQLException
import java.time.LocalDateTime


@RestControllerAdvice
class SqlExecutorHandler {

    @ExceptionHandler(*[SQLException::class])
    fun <T : SQLException> handlerNotFoundException(ex: T, request: ServletWebRequest): ResponseEntity<Any?>? {
        val path = request.request.requestURI
        val error = ApiError().apply {
            message = ex.message
            status = HttpStatus.BAD_REQUEST
            timestamp = LocalDateTime.now()
            this.path = path
        }
        return ResponseEntity(error,HttpStatus.BAD_REQUEST)
    }
}