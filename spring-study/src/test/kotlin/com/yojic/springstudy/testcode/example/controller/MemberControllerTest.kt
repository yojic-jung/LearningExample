package com.yojic.springstudy.testcode.example.controller

import com.yojic.springstudy.testcode.example.dto.MemberDto
import com.yojic.springstudy.testcode.example.entity.MemberEntity
import com.yojic.springstudy.testcode.example.controller.MemberController
import com.yojic.springstudy.testcode.example.service.MemberService
import com.yojic.springstudy.testcode.example.service.MemberNoneService
import com.yojic.springstudy.testcode.example.service.MemberNothingService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

/**
 * Def : @WebMvcTest 사용시 목 객체 사용법
 * Desc :
 *       class 위에 @MockBean(target class)을 선언하면
 *       @Autowired로 주입 받을 수 있음,
 *       물론 lateinit도 가능하지만 굳이 Autowired 가능한데 lateinit할 필요 없어보임
 *
 *       class 위에 선언하지 @MockBean 선언하지 않으면
 *       class body에 lateinit var로 주입 받아야함
 */
@WebMvcTest(MemberController::class)
@MockBean(MemberService::class)
@MockBean(MemberNoneService::class)
class MemberControllerTest(
    @Autowired
    private val memberController: MemberController,
    @Autowired
    private val memberService: MemberService
) {
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var memberNoneService: MemberNoneService
    @MockBean
    lateinit var memberNothingService: MemberNothingService

    @Test
    fun `가짜 객체 테스트`() {
        assertThat(Mockito.mockingDetails(memberController).isMock).isFalse()
        assertThat(Mockito.mockingDetails(memberService).isMock).isTrue()
        assertThat(Mockito.mockingDetails(memberNoneService).isMock).isTrue()
        assertThat(Mockito.mockingDetails(memberNothingService).isMock).isTrue()
    }

    @Test
    fun `MemberController 테스트`() {
        // given
        val name = "Tom"
        val age = 22
        val memberDto = MemberDto(name = name, age = age)
        `when`(memberService.register(memberDto)).thenReturn(MemberEntity(id = 1, name = name, age = age))

        // when
        val result = memberController.register(memberDto)

        // then
        assertThat(result.statusCode.value()).isEqualTo(200)
    }

    @Test
    fun `MemberController 네거티브 테스트`() {
        // given
        val memberDto = MemberDto(name = "", age = 22)

        // when
        val result = memberController.register(memberDto)

        // then
        assertThat(result.statusCode.value()).isEqualTo(200)
    }

}