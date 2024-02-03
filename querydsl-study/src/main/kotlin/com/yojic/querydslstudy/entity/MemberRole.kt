package com.yojic.querydslstudy.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
open class MemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var memRoleId: Long = 0,
    open var memId: Long = 0,
    open var enabled: Boolean,
    open var roleName: String = "",
    @CreationTimestamp
    open var sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    open var sysCreateTime: LocalDateTime = LocalDateTime.now(),
)
