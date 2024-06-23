package com.yojic.springstudy.transaction.proxy.dynamic

import com.yojic.springstudy.transaction.proxy.compile.MemberService
import com.yojic.springstudy.transaction.proxy.compile.MemberServiceImpl
import com.yojic.springstudy.transaction.proxy.sample.MemberDto
import com.yojic.springstudy.transaction.proxy.sample.MemberMapper
import com.yojic.springstudy.transaction.proxy.sample.MemberRepository
import com.yojic.springstudy.transaction.proxy.sample.MemberRoleRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.PlatformTransactionManager

@SpringBootTest
class MemberServiceDynamicProxyTest(
    @Autowired private val transactionManager: PlatformTransactionManager,
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
    fun `다이내믹 트랜잭션 프록시 적용 - 성공`() {
        val factoryBean =
            TxProxyFactoryBean(
                target = memberServiceImpl,
                transactionManager = transactionManager,
                methodNamePatter = "create",
                serviceInterface = MemberService::class.java,
            )
        val memberDto = MemberDto(id = 11, age = AGE)
        try {
            val memberServiceProxy = factoryBean.`object` as MemberService
            memberServiceProxy.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(11)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(true)
    }

    @Test
    fun `다이내믹 트랜잭션 프록시 미적용 - 성공`() {
        val memberDto = MemberDto(id = ID, age = AGE)
        try {
            memberServiceImpl.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(ID)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(false)
    }
}
