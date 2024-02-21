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
    val memberRoleId: Int = 0,
    val memberId: Int = 0,
    val role: String = "",
)
