package com.example.sqlexecutorservice.service

import com.example.sqlexecutorservice.dao.SqlDao
import com.example.sqlexecutorservice.dto.SqlDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SqlExecuteService(
    @Autowired
    val sqlDao: SqlDao
) {
    fun executeSelectQuery(sqlDto: SqlDto): List<Any> {
        return sqlDao.selectExecute(sqlDto.query)
    }

}