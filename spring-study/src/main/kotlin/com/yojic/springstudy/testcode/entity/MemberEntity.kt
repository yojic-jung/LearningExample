package com.yojic.springstudy.testcode.entity

import jakarta.persistence.Entity
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class MemberEntity(
    val id: Int,
    val name: String,
    val age: Int,
    @CreationTimestamp
    val sysCreateDate: LocalDateTime? = null
)