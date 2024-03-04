package com.yojic.springstudy.testcode.service

import com.yojic.springstudy.testcode.dto.MemberDto
import com.yojic.springstudy.testcode.entity.MemberEntity
import com.yojic.springstudy.testcode.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun register(memberDto: MemberDto) {
        val memberEntity = MemberEntity(id = 0, name = memberDto.name, age = memberDto.age)
        memberRepository.save(memberEntity)
    }
}