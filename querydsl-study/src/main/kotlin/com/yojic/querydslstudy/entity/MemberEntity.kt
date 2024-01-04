package com.yojic.querydslstudy.entity

import jakarta.persistence.*
import jakarta.persistence.GenerationType.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity(name = "Member")
class MemberEntity(
// open 키워드 사용하지 않아도 정상 작동(하이버네이트 5.0 이상부터 bytecode enhancement 방식으로 지원)
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var memId: Long = 0,
    var email: String = "",
    var passwd: String = "",
    var humanStatus: Int = 0,
    var failCnt: Int = 0,
    var lastFailTime: String? = null,
    @CreationTimestamp
    var sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var sysCreateTime: LocalDateTime = LocalDateTime.now(),
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "memId", referencedColumnName = "memId", insertable = false, updatable = false)
    var roles: List<MemberRoleEntity>? = null,
)
