package com.yojic.springstudy.jdbc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

@SpringBootTest
class JdbcTest(
    @Autowired val jdbcTemplate: JdbcTemplate,
    @Autowired val userService: UserService,
    @Autowired val jdbcUserService: JdbcUserService,
) {

    companion object {
        const val id: Long = 1
        const val email = "test@test.com"
    }

    @BeforeEach
    fun `초기화`() {
        // 테이블 삭제
        val dropTableQuery = "DROP TABLE IF EXISTS users"
        jdbcTemplate.execute(dropTableQuery)

        // 테이블 생성
        val createTableQuery = """
                    CREATE TABLE users (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(100) NOT NULL
                        )
                        """
        jdbcTemplate.execute(createTableQuery)

        // 데이터 생성
        val sql = "INSERT INTO users (email) VALUES (?)"
        jdbcTemplate.update(sql, "michale@test.com")
    }

    private fun getUserById(id: Long): String {
        val selectSql = "SELECT email FROM users where id = ?"
        return jdbcTemplate.queryForObject(sql = selectSql, args = arrayOf(id)) { rs, _ ->
            rs.getString("email")
        }
    }

    @Test
    fun `connection 직접 사용`() {
        userService.updateUserEmail(id, email)
        assertThat(getUserById(id)).isEqualTo(email)
    }

    @Test
    fun `콜백 생성하여 jdbc템플릿 메서드에 전달`() {
        jdbcUserService.updateUserEmail(id, email)
        assertThat(getUserById(id)).isEqualTo(email)
    }

    @Test
    fun `jdbc 내부 콜백 사용`() {
        jdbcUserService.simpleUpdateUserEmail(id, email)
        assertThat(getUserById(id)).isEqualTo(email)
    }
}
