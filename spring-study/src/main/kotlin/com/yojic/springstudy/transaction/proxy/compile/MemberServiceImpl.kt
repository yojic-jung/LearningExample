package com.yojic.springstudy.transaction.proxy.compile

import com.yojic.springstudy.transaction.proxy.sample.*
import org.springframework.stereotype.Component

@Component
class MemberServiceImpl(
    private val memberRepo: MemberRepository,
    private val memberRoleRepo: MemberRoleRepository,
    private val memberMapper: MemberMapper,
) : MemberService {
    override fun create(memberDto: MemberDto) {
        val memberEntity = memberRepo.save(memberMapper.memberToEntity(memberDto))
        memberRoleRepo.save(memberMapper.roleToEntity(memberEntity, "USER"))
    }
}
