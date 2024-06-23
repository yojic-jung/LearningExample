package com.yojic.springstudy.transaction.proxy.compile

import com.yojic.springstudy.transaction.proxy.sample.*
import org.springframework.stereotype.Component

@Component("memberServiceImpl")
class MemberServiceImpl(
    private val memberRepo: MemberRepository,
    private val memberRoleRepo: MemberRoleRepository,
    private val memberMapper: MemberMapper,
) : MemberService {
    override fun create(memberDto: MemberDto) {
        val mem = memberMapper.memberToEntity(memberDto)
        val memberEntity = memberRepo.save(mem)

        // 트랜잭션 테스트를 위해 Exception을 던짐
        throw RuntimeException()

        memberRoleRepo.save(memberMapper.roleToEntity(memberEntity, "USER"))
    }
}
