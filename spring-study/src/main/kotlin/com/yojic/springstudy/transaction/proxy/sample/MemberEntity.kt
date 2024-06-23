package com.yojic.springstudy.transaction.proxy.sample

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "member")
@Entity
class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var age: Int = 0

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    var role: MutableList<MemberRoleEntity> = mutableListOf()

    constructor(id: Long, age: Int) {
        this.id = id
        this.age = age
    }
}
