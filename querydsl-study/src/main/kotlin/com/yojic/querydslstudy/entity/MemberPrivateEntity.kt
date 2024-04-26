package com.yojic.querydslstudy.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class MemberPrivateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memPrvcId: Long,
    var memId: Long = 0,
    var userName: String,
    var birth: String = "",
    var phone: String = "",
    @CreationTimestamp
    var sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var sysCreateTime: LocalDateTime = LocalDateTime.now()
)
