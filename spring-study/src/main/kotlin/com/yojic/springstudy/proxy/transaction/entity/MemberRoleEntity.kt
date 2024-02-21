package com.yojic.springstudy.proxy.transaction.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "member_role")
class MemberRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberRoleId: Int = 0,  // jdbcTemplate 사용하려면 var로 선언
    var memberId: Int = 0,
    var role: String = "",
)
