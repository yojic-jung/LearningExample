package com.yojic.springstudy.testcode.controller

import com.yojic.springstudy.testcode.dto.MemberDto
import com.yojic.springstudy.testcode.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Def : 컨트롤러 테스트 목적
 * Desc: WebMvcTest 통해 컨트롤러 슬라이스 테스트 시 성능 확인과 테스트 가능 범위(또는 대상) 파악
 */
@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/member")
    fun register(@Validated @RequestBody memberDto: MemberDto): ResponseEntity<Any> {
        memberService.register(memberDto)
        return ResponseEntity.ok(true)
    }
}