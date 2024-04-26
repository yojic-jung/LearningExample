package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.dto.MemberDto
import com.yojic.querydslstudy.entity.MemberEntity

interface MemberQueryRepository {
    fun findAllMembers(): List<MemberEntity>?

    fun findAllMembersWithRoles(): List<MemberEntity>?

    fun findMembersByMemIdFetchJoin(memId: Long): MemberEntity?

    fun findMembersByMemIdNoFetchJoin(memId: Long): MemberEntity?

    fun findAllMembersEmail(): List<MemberDto>?

    fun findMemberEmailOnlyOne(): MemberEntity?

    fun findMemberEmailLimitOne(): MemberEntity?
}
