package com.yojic.springstudy.proxy.transaction.dao

import com.yojic.springstudy.proxy.transaction.dto.MemberRoleDto
import com.yojic.springstudy.proxy.transaction.entity.MemberEntity
import com.yojic.springstudy.proxy.transaction.entity.MemberRoleEntity
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component
import java.sql.PreparedStatement
import java.sql.Statement

@Component
class MemberRoleDao(
    private val jdbcTemplate: JdbcTemplate,
) {
    fun deleteAll(): Int {
        val sql = "DELETE FROM MEMBER_ROLE"
        return jdbcTemplate.update(sql)
    }
    fun findById(memberId: Int): MemberRoleEntity? {
        val sql = "SELECT * FROM member_role WHERE member_role_id = ?"
        val memberRoleList = jdbcTemplate.query(sql, arrayOf(memberId), BeanPropertyRowMapper(MemberRoleEntity::class.java))
        return if(memberRoleList.isEmpty()) null else memberRoleList[0]
    }

    fun save(memberRole: MemberRoleDto): Int {
        val sql = "insert into member_role(member_id, role) values (?, ?)"

        val keyHolder: KeyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({ connection ->
            val ps: PreparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ps.setString(1, memberRole.memberId.toString())
            ps.setString(2, memberRole.role)
            ps
        }, keyHolder)

        return keyHolder.key?.toInt() ?: throw RuntimeException("Fail")
    }
}
