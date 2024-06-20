package com.yojic.springstudy.transaction.reflection.transaction.entity

import jakarta.persistence.*

@Entity
@Table(name = "member_role")
class MemberRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,  // jdbcTemplate 사용하려면 var로 선언
    @Column(name = "member_id")
    var memberId: Int = 0,
    var role: String = "",
)
