package com.yojic.querydslstudy.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
open class MemberPrivate(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var memPrvcId: Long = 0,
    open var memId: Long = 0,
    open var userName: String,
    open var birth: String = "",
    open var phone: String = "",
    @CreationTimestamp
    open var sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    open var sysCreateTime: LocalDateTime = LocalDateTime.now()
)
