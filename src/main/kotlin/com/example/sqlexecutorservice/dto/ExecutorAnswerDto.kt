package com.example.sqlexecutorservice.dto



data  class ExecutorAnswerDto (
    var userName: String? = null,
    var categoryId: Long? =null,
    var taskId: Long? = null,
    var userSql: String? = null,
    var resultStatus: String? = null,
    var messageException: String = ""
)
