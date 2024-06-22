package com.yojic.springstudy.transaction.proxy.sample

import jakarta.persistence.*

@Table(name = "member_role")
@Entity
class MemberRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: MemberEntity? = null

    var role: String = "USER"

    constructor(member: MemberEntity?, role: String) {
        this.member = member
        this.role = role
    }
}
