package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.dto.MemberDto
import com.yojic.querydslstudy.entity.Member

interface MemberQueryRepository {
    fun findAllMembers(): List<Member>?

    fun findAllMembersWithRoles(): List<Member>?

    fun findMembersByMemIdFetchJoin(memId: Long): Member?

    fun findMembersByMemIdNoFetchJoin(memId: Long): Member?

    fun findAllMembersEmail(): List<MemberDto>?

    fun findMemberEmailOnlyOne(): Member?

    fun findMemberEmailLimitOne(): Member?
}
