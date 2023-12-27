package com.yojic.querydslstudy.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity(name = "MemberPrivate")
class MemberPrivateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memPrvcId: Long = 0,
    val memId: Long = 0,
    val userName: String,
    val birth: String = "",
    val phone: String = "",
    @CreationTimestamp
    val sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    val sysCreateTime: LocalDateTime = LocalDateTime.now(),
)
