package com.yojic.springstudy.transaction.proxy.compile

class MemberServiceImpl(private val memberRepo: MemberRepository) : MemberService {
    override fun create(member: Member) {
        val id = memberRepo.create(member)
        memberRepo.addRole(MemberRole(id, "USER"))
    }

}
