package com.example.sqlexecutorservice.contoller

import com.example.sqlexecutorservice.dto.ApiResponse
import com.example.sqlexecutorservice.dto.SqlDto
import com.example.sqlexecutorservice.service.SqlExecuteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/sql-executor/execute")
@CrossOrigin(origins = ["http://localhost:3000"])
class SqlExecuteController(@Autowired val sqlExecuteService: SqlExecuteService) {

    @PostMapping
    fun fetchData(@RequestBody sqlDto: SqlDto): ResponseEntity<ApiResponse> {
        println(sqlDto)
        val response = ApiResponse().apply {
            status = HttpStatus.OK
            response = sqlExecuteService.executeSelectQuery(sqlDto)
        }
        return ResponseEntity.ok(response)
    }
}