/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.yojic.querydslstudy.entity

import jakarta.persistence.* // ktlint-disable no-wildcard-imports
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Table(name = "member")
@Entity
class MemberEntity(
    id: Long,
    email: String,
    passwd: String,
    role: MutableList<MemberRoleEntity>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        protected set
    var email: String = email
        protected set
    var passwd: String = passwd
        protected set
    var isSocial: Boolean = false
        protected set
    var humanStatus: Int = 0
        protected set
    var failCnt: Int = 0
        protected set
    var lastFailTime: LocalDateTime? = null
        protected set

    @CreationTimestamp
    var sysCreateTime: LocalDateTime = LocalDateTime.now()
        protected set

    @UpdateTimestamp
    var sysUpdateTime: LocalDateTime = LocalDateTime.now()
        protected set

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", referencedColumnName = "id")
    var roles: MutableList<MemberRoleEntity> = role
        protected set

    // 로그인 실패 정보 기록
    fun updateFailCnt(failCnt: Int) {
        this.failCnt = failCnt
        this.lastFailTime = LocalDateTime.now()
        this.sysUpdateTime = LocalDateTime.now()
        if (failCnt == 5) {
            this.humanStatus = 1
        }
    }

    // 사용자 권한 추가
    fun addRole(role: MemberRoleEntity) {
        this.roles.add(role)
    }

    constructor(id: Long, email: String, passwd: String, role: MutableList<MemberRoleEntity>, failCnt: Int) : this(id, email, passwd, role) {
        this.id = id
        this.email = email
        this.passwd = passwd
        this.roles = role
        this.failCnt = failCnt
    }

    constructor(
        id: Long,
        email: String,
        isSocial: Boolean,
        role: MutableList<MemberRoleEntity>
    ) : this(id, email, "", role) {
        this.id = id
        this.email = email
        this.isSocial = isSocial
        this.roles = role
    }
}
