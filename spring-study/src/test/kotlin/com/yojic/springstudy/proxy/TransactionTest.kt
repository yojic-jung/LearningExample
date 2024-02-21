package com.yojic.springstudy.proxy

import com.yojic.springstudy.proxy.transaction.dto.MemberDto
import com.yojic.springstudy.proxy.transaction.factory.TxProxyFactoryBean
import com.yojic.springstudy.proxy.transaction.service.IMemberService
import com.yojic.springstudy.proxy.transaction.service.MemberServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TransactionTest(
    @Autowired
    private val txFactory: TxProxyFactoryBean,
    @Autowired
    private val memberServiceImpl: MemberServiceImpl
) {
    @Test
    fun `프록시 트랜잭션 테스트`() {
        // given
        val member = MemberDto(name = "John", age = 20)

        // when
        val proxy = txFactory.getObject() as IMemberService
        val proxyMethodResult = proxy.registerMember(member)

        // then
        val memberId = proxyMethodResult.get("memberId")
        val memberRoleId = proxyMethodResult.get("memberRoleId")
        val memberEntity = proxy.findMemberById(memberId!!)
        val memberRoleEntity = proxy.findMemberRoleById(memberRoleId!!)
        assertThat(memberEntity).isNotNull
        assertThat(memberRoleEntity).isNotNull
    }

    @Test
    fun `프록시 트랜잭션 롤백 테스트`() {
        // given
        val member = MemberDto(name = "John", age = 20)
        val proxy = txFactory.getObject() as IMemberService
        try {
            // when
            proxy.registerMemberFail(member)
        } catch (e: Exception) {
            // then
            val memberEntity = proxy.findMemberById(1)
            val memberRoleEntity = proxy.findMemberRoleById(1)
            assertThat(memberEntity).isNull()
            assertThat(memberRoleEntity).isNull()
        }
    }

    @Test
    fun `타깃 논-트랜잭션 테스트`() {
        // given
        val member = MemberDto(name = "John", age = 20)
        try {
            // when
            memberServiceImpl.registerMemberFail(member)
        } catch (e: Exception) {
            // then
            val memberEntity = memberServiceImpl.findMemberById(1)
            val memberRoleEntity = memberServiceImpl.findMemberRoleById(1)
            assertThat(memberEntity).isNotNull
            println(memberEntity!!.memberId)
            assertThat(memberEntity!!.memberId).isGreaterThan(0)
            assertThat(memberEntity!!.name).isEqualTo(member.name)
            assertThat(memberEntity!!.age).isEqualTo(member.age)
            assertThat(memberRoleEntity).isNull()
        }
    }
}
