package com.yojic.querydslstudy.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Table(name = "member_role_data")
@Entity
data class MemberRoleDataEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memRoleId: Long = 0,
    var memId: Long = 0,
    var enabled: Boolean,
    var roleName: String = "",
    @CreationTimestamp
    var sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var sysCreateTime: LocalDateTime = LocalDateTime.now(),
)
