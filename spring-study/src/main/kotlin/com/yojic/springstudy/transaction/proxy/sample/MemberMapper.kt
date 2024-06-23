package com.yojic.springstudy.transaction.proxy.sample

import org.springframework.stereotype.Component

@Component
class MemberMapper {
    fun memberToEntity(memberDto: MemberDto): MemberEntity = MemberEntity(id = memberDto.id, age = memberDto.age)

    fun roleToEntity(
        memberEntity: MemberEntity,
        role: String,
    ): MemberRoleEntity = MemberRoleEntity(memberEntity, role)
}
