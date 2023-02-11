package com.example.sqlexecutorservice.dto

import org.springframework.http.HttpStatus


class ApiError {
    var message: String? = null
    var status: HttpStatus? = null

    //var timestamp: LocalDateTime? = null
    var path: String? = null
}