/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
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

@Entity
open class Member(
    // open 키워드 사용하지 않아도 정상 작동(하이버네이트 5.0 이상부터 bytecode enhancement 방식으로 지원)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var memId: Long = 0,
    open var email: String = "",
    open var passwd: String = "",
    open var humanStatus: Int = 0,
    open var failCnt: Int = 0,
    open var lastFailTime: String? = null,
    @CreationTimestamp
    open var sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    open var sysCreateTime: LocalDateTime = LocalDateTime.now(),
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "memId", referencedColumnName = "memId", insertable = false, updatable = false)
    open var role: MutableList<MemberRole>? = null,
)
