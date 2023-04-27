package com.example.sqlexecutorservice.dto

import com.example.testsqlexecuter.dto.Status


data  class ExecutorAnswerDto (
    var username: String? = null,
    var moduleId: Int? =null,
    var taskId: Int? = null,
    var userSql: String? = null,
    var database: String? = null,
    var resultStatus: Status? = null,
    var messageException: String = ""
)
