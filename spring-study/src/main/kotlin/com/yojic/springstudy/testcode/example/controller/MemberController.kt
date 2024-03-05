package com.yojic.springstudy.testcode.example.controller

import com.yojic.springstudy.testcode.example.dto.MemberDto
import com.yojic.springstudy.testcode.example.service.MemberService
import com.yojic.springstudy.testcode.example.service.MemberNoneService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Def : 컨트롤러 슬라이스 테스트 목적
 */
@RestController
class MemberController(
    private val memberService: MemberService,
    private val memberNoneService: MemberNoneService
) {

    @PostMapping("/member")
    fun register(@Validated @RequestBody memberDto: MemberDto): ResponseEntity<Any> {
        val result = memberService.register(memberDto)
        println(result)
        return ResponseEntity.ok(true)
    }
}