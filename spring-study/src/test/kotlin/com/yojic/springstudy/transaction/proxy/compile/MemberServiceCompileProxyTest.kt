package com.yojic.springstudy.transaction.proxy.compile

import com.yojic.springstudy.transaction.proxy.sample.MemberDto
import com.yojic.springstudy.transaction.proxy.sample.MemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceCompileProxyTest(
    @Autowired @Qualifier("compileProxy") private val memberService: MemberService,
    @Autowired private val memberServiceImpl: MemberServiceImpl,
    @Autowired private val memberRepo: MemberRepository,
) {
    companion object {
        const val ID = 1L
        const val AGE = 20
    }

    @BeforeEach
    fun `테스트용 더미 데이터 삭제`() {
        memberRepo.deleteAll()
        println("execute")
    }

    @Test
    fun `스프링 트랜잭션 프록시 적용 - 성공`() {
        val memberDto = MemberDto(id = ID, age = AGE)
        try {
            memberService.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(ID)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(true)
    }

    @Test
    fun `스프링 트랜잭션 프록시 미적용 - 성공`() {
        val memberDto = MemberDto(id = ID, age = AGE)
        try {
            memberServiceImpl.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(ID)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(false)
    }
}
