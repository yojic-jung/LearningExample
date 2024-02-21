package com.yojic.springstudy.proxy.transaction.entity

import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Int = 0,
    val name: String = "",
    val age: Int = 0,
)
