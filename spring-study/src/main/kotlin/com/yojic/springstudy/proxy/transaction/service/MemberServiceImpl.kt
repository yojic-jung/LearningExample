package com.yojic.springstudy.proxy.transaction.service

import com.yojic.springstudy.proxy.transaction.dao.MemberDao
import com.yojic.springstudy.proxy.transaction.dao.MemberRoleDao
import com.yojic.springstudy.proxy.transaction.dto.MemberDto
import com.yojic.springstudy.proxy.transaction.dto.MemberRoleDto
import com.yojic.springstudy.proxy.transaction.entity.MemberEntity
import com.yojic.springstudy.proxy.transaction.entity.MemberRoleEntity

class MemberServiceImpl(
    private val memberDao: MemberDao,
    private val memberRoleDao: MemberRoleDao,
) : IMemberService {

    override fun findMemberById(id: Int): MemberEntity? {
        return memberDao.findById(id)
    }

    override fun findMemberRoleById(id: Int): MemberRoleEntity? {
        return memberRoleDao.findById(id)
    }

    override fun registerMember(memberDto: MemberDto): Map<String, Int> {
        val memberId = memberDao.save(memberDto)
        val memberRoleId = memberRoleDao.save(MemberRoleDto(memberId = memberId, role = "member"))
        return mapOf("memberId" to memberId, "memberRoleId" to memberRoleId)
    }

    override fun registerMemberFail(memberDto: MemberDto): Map<String, Int> {
        val memberId = memberDao.save(memberDto)
        throw Exception()
        val memberRoleId = memberRoleDao.save(MemberRoleDto(memberId = memberId, role = "member"))
        return mapOf("memberId" to memberId, "memberRoleId" to memberRoleId)
    }
}
