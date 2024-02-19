package com.yojic.springstudy.jdbc

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.PreparedStatement

@Service
class JdbcUserService(
    private val jdbcTemplate: JdbcTemplate,
) {
    fun updateUserEmail(id: Long, newEmail: String): Int {
        return jdbcTemplate.update(object : PreparedStatementCreator {
            override fun createPreparedStatement(con: Connection): PreparedStatement {
                val preStmt = con.prepareStatement("UPDATE users SET email = ? WHERE id = ?")
                preStmt.setString(1, newEmail)
                preStmt.setLong(2, id)
                return preStmt
            }
        })
    }

    fun simpleUpdateUserEmail(id: Long, newEmail: String): Int {
        return jdbcTemplate.update("UPDATE users SET email = ? WHERE id = ?", newEmail, id)
    }
}
