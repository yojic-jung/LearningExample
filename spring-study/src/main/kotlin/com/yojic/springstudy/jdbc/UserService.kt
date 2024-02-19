package com.yojic.springstudy.jdbc

import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import javax.sql.DataSource

@Service
class UserService(
    private val dataSource: DataSource,
) {
    fun updateUserEmail(id: Long, newEmail: String): Int {
        var resultCnt = 0
        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        try {
            connection = dataSource.connection
            val sql = "UPDATE users SET email = ? WHERE id = ? "
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, newEmail)
            preparedStatement.setLong(2, id)
            resultCnt = preparedStatement.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            try {
                preparedStatement?.close()
                connection?.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        return resultCnt
    }
}
