package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberEntity
import org.springframework.stereotype.Repository

interface MemberQueryRepository {
    fun findAllMembers(): List<MemberEntity>

    fun findAllMembersWithRoles(): List<MemberEntity>
}
