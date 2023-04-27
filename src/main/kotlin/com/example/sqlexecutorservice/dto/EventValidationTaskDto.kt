package com.example.sqlexecutorservice.dto

data class EventValidationTaskDto(
    var username: String? = null,
    var moduleId: Int? = null,
    var taskId: Int? = null,
    var userSql: String? = null,
    var correctSql: String? = null,
    var database: String? = null
)
