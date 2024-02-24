package com.yojic.springstudy.proxy.transaction.dao

import com.yojic.springstudy.proxy.transaction.dto.MemberDto
import com.yojic.springstudy.proxy.transaction.entity.MemberEntity
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component
import java.sql.PreparedStatement
import java.sql.Statement.RETURN_GENERATED_KEYS

@Component
class MemberDao(
        private val jdbcTemplate: JdbcTemplate,
) {
    fun deleteAll(): Int {
        val sql = "DELETE FROM MEMBER"
        return jdbcTemplate.update(sql)
    }

    fun findById(memberId: Int): MemberEntity? {
        val sql = "SELECT * FROM member WHERE member_id = ?"
        val memberList = jdbcTemplate.query(sql, arrayOf(memberId), BeanPropertyRowMapper(MemberEntity::class.java))
        return if (memberList.isEmpty()) null else memberList[0]
    }

    fun save(member: MemberDto): Int {
        val sql = "insert into member(name, age) values (?, ?)"

        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({ connection ->
            val ps: PreparedStatement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS)
            ps.setString(1, member.name)
            ps.setString(2, member.age.toString())
            ps
        }, keyHolder)

        return keyHolder.key?.toInt() ?: throw RuntimeException("Fail")
    }
}
