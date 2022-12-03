package com.example.sqlexecutorservice.dto

import org.springframework.http.HttpStatus

class ApiResponse{
    var status: HttpStatus? = null
    var response: List<Any>? = null
}
