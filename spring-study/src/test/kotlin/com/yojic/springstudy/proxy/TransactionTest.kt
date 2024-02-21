package com.yojic.springstudy.proxy

import com.yojic.springstudy.proxy.transaction.dto.MemberDto
import com.yojic.springstudy.proxy.transaction.factory.TxProxyFactoryBean
import com.yojic.springstudy.proxy.transaction.service.IMemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.reflect.InvocationTargetException

@SpringBootTest
class TransactionTest(
    @Autowired
    private val txFactory: TxProxyFactoryBean,
) {
    @Test
    fun `트랜잭션 프록시 테스트`() {
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
    fun `트랜잭션 프록시 롤백 테스트`() {
        val member = MemberDto(name = "John", age = 20)
        val proxy = txFactory.getObject() as IMemberService
        try {
            proxy.registerMemberFail(member)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val memberEntity = proxy.findMemberById(1)
        val memberRoleEntity = proxy.findMemberRoleById(1)
        println(memberEntity)
        println(memberRoleEntity)
        assertThat(memberEntity).isNull()
        assertThat(memberRoleEntity).isNull()
    }
}
