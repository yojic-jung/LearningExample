package com.yojic.springstudy.proxy.transaction.entity

import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberId: Int = 0, // jdbcTemplate 사용하려면 var로 선언
    var name: String = "",
    var age: Int = 0,
)
