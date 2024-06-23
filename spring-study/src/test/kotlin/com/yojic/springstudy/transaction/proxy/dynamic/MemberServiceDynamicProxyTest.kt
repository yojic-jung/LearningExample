package com.yojic.springstudy.transaction.proxy.dynamic

import com.yojic.springstudy.transaction.proxy.compile.MemberService
import com.yojic.springstudy.transaction.proxy.compile.MemberServiceImpl
import com.yojic.springstudy.transaction.proxy.sample.MemberDto
import com.yojic.springstudy.transaction.proxy.sample.MemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class MemberServiceDynamicProxyTest(
    @Autowired private val memberRepo: MemberRepository,
    @Autowired private val memberServiceImpl: MemberServiceImpl,
    @Autowired private val transactionManager: PlatformTransactionManager,
) {
    companion object {
        const val ID = 1L
        const val AGE = 20
    }

    @BeforeEach
    fun `테스트용 더미 데이터 삭제`() {
        memberRepo.deleteById(ID)
        println("execute")
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
        val memberDto = MemberDto(id = 2, age = AGE)
        try {
            val memberServiceProxy = factoryBean.`object` as MemberService
            memberServiceProxy.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(2)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(true)
    }

    @Test
    fun `다이내믹 트랜잭션 프록시 미적용 - 성공`() {
        println(memberServiceImpl is MemberServiceImpl)

        val memberDto = MemberDto(id = 5, age = AGE)
        try {
            memberServiceImpl.create(memberDto)
        } catch (e: RuntimeException) {
        }

        val memberEntity = memberRepo.findById(5)
        println(memberEntity)
        Assertions.assertThat(memberEntity.isEmpty).isEqualTo(false)
    }
}
