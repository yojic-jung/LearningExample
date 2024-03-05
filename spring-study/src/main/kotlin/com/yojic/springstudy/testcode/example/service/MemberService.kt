package com.yojic.springstudy.testcode.example.service

import com.yojic.springstudy.testcode.example.dto.MemberDto
import com.yojic.springstudy.testcode.example.entity.MemberEntity
import com.yojic.springstudy.testcode.example.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun register(memberDto: MemberDto): MemberEntity {
        return MemberEntity(id = 0, name = memberDto.name, age = memberDto.age)
    }
}