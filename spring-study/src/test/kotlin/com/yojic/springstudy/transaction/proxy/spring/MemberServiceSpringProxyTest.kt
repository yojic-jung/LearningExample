package com.yojic.springstudy.transaction.proxy.spring

import com.yojic.springstudy.transaction.proxy.compile.MemberService
import com.yojic.springstudy.transaction.proxy.compile.MemberServiceImpl
import com.yojic.springstudy.transaction.proxy.sample.MemberDto
import com.yojic.springstudy.transaction.proxy.sample.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceSpringProxyTest(
    @Autowired @Qualifier("memberServiceProxy") private val memberService: MemberService,
    @Autowired private val memberServiceImpl: MemberServiceImpl,
    @Autowired private val memberRepo: MemberRepository,
) {
    companion object {
        const val ID = 1L
        const val AGE = 20
    }

    @Test
    fun `스프링 트랜잭션 프록시 적용 - 성공`() {
        val memberDto = MemberDto(id = ID, age = AGE)
        try {
            memberService.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(ID)
        assertThat(memberEntity.isEmpty).isEqualTo(true)
    }

    @Test
    fun `스프링 트랜잭션 프록시 미적용 - 성공`() {
        val memberDto = MemberDto(id = ID, age = AGE)
        try {
            memberServiceImpl.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(ID)
        assertThat(memberEntity.isEmpty).isEqualTo(false)
    }
}
