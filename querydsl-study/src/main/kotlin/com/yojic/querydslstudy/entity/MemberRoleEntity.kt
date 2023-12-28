package com.yojic.querydslstudy.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity(name = "MemberRole")
class MemberRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memRoleId: Long = 0,
    val memId: Long = 0,
    val enabled: Boolean,
    val roleName: String = "",
    @CreationTimestamp
    val sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    val sysCreateTime: LocalDateTime = LocalDateTime.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memId", referencedColumnName = "memId", insertable = false, updatable = false)
    var member: MemberEntity? = null
)
