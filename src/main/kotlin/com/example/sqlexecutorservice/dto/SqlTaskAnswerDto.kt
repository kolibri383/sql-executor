package com.example.sqlexecutorservice.dto

data class SqlTaskAnswerDto(
    val userName: String? = null,
    val categoryId: Long? = null,
    val taskId: Long? = null,
    val userSql: String? = null,
    val correctSql: String? = null,
)