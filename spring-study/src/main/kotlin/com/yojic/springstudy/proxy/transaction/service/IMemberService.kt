package com.yojic.springstudy.proxy.transaction.service

import com.yojic.springstudy.proxy.transaction.dto.MemberDto
import com.yojic.springstudy.proxy.transaction.entity.MemberEntity
import com.yojic.springstudy.proxy.transaction.entity.MemberRoleEntity

interface IMemberService {

    fun findMemberById(id: Int): MemberEntity?

    fun findMemberRoleById(id: Int): MemberRoleEntity?

    fun registerMember(memberDto: MemberDto): Map<String, Int>

    fun registerMemberFail(memberDto: MemberDto): Map<String, Int>
}
