package com.yojic.springstudy.transaction.reflection.transaction.entity

import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0, // jdbcTemplate 사용하려면 var로 선언
    var name: String = "",
    var age: Int = 0,
)
