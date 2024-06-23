package com.yojic.springstudy.transaction.proxy.compile

import com.yojic.springstudy.transaction.proxy.sample.MemberDto
import com.yojic.springstudy.transaction.proxy.sample.MemberMapper
import com.yojic.springstudy.transaction.proxy.sample.MemberRepository
import com.yojic.springstudy.transaction.proxy.sample.MemberRoleRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceCompileProxyTest(
    @Autowired @Qualifier("compileProxy") private val memberService: MemberService,
    @Autowired private val memberRepo: MemberRepository,
    @Autowired private val memberRoleRepo: MemberRoleRepository,
    @Autowired private val memberMapper: MemberMapper,
) {
    lateinit var memberServiceImpl: MemberServiceImpl

    companion object {
        const val ID = 1L
        const val AGE = 20
    }

    @BeforeEach
    fun beforeEach() {
        memberRepo.deleteAll()
        memberServiceImpl =
            MemberServiceImpl(
                memberRepo,
                memberRoleRepo,
                memberMapper,
            )
    }

    @Test
    fun `스프링 컴파일 프록시 적용 - 성공`() {
        memberRepo.deleteAll()

        val memberDto = MemberDto(id = ID, age = AGE)
        try {
            memberService.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(ID)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(true)
    }

    @Test
    fun `스프링 컴파일 프록시 미적용 - 성공`() {
        val memberDto = MemberDto(id = ID, age = AGE)
        try {
            memberServiceImpl.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(ID)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(false)
    }
}
