package com.example.sqlexecutorservice.dto


data  class ExecutorAnswerDto (
    var username: String? = null,
    var fullName: String? = null,
    var moduleId: Int? =null,
    var taskId: Int? = null,
    var userSql: String? = null,
    var database: String? = null,
    var resultStatus: Status? = null,
    var messageException: String = ""
)
