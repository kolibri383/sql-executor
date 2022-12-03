package com.example.sqlexecutorservice.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class SqlDao(
    @Autowired
    val jdbcTemplate: JdbcTemplate
) {
    @Transactional(readOnly = true)
    fun sqlExecute(sql: String): List<Any> {
        return jdbcTemplate.queryForList(sql)
    }
}