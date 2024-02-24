package com.yojic.springstudy.proxy

import com.yojic.springstudy.proxy.transaction.dto.MemberDto
import com.yojic.springstudy.proxy.transaction.factory.TxProxyFactoryBean
import com.yojic.springstudy.proxy.transaction.service.IMemberService
import com.yojic.springstudy.proxy.transaction.service.MemberServiceImpl
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate


@SpringBootTest
class TransactionTest(
        @Autowired private val txFactory: TxProxyFactoryBean,
        @Autowired private val memberServiceImpl: MemberServiceImpl,
        @Autowired private val jdbcTemplate: JdbcTemplate,
        @Qualifier("springProxy") @Autowired private val memberService: ProxyFactoryBean,
) {
    @BeforeEach
    fun `초기화`() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS MEMBER")
        jdbcTemplate.execute("DROP TABLE IF EXISTS MEMBER_ROLE")
        jdbcTemplate.execute("CREATE TABLE MEMBER (" + "member_id INT PRIMARY KEY AUTO_INCREMENT," + "name VARCHAR(255)," + "age INT)")
        jdbcTemplate.execute("CREATE TABLE MEMBER_ROLE (" + "member_role_id INT PRIMARY KEY AUTO_INCREMENT," + "member_id INT," + "ROLE VARCHAR(255))")
    }

    @Test
    fun `다이내믹 프록시 트랜잭션 테스트`() {
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
    fun `다이내믹 프록시 트랜잭션 롤백 테스트`() {
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
            assertThat(memberEntity!!.memberId).isGreaterThan(0)
            assertThat(memberEntity.name).isEqualTo(member.name)
            assertThat(memberEntity.age).isEqualTo(member.age)
            assertThat(memberRoleEntity).isNull()
        }
    }

    @Test
    fun `스프링 프록시팩토리 빈 테스트`() {
        // given
        val member = MemberDto(name = "John", age = 20)
        val proxy = memberService.`object` as IMemberService

        // when
        val proxyMethodResult = proxy.registerMember(member)

        // then
        val memberId = proxyMethodResult.get("memberId")
        val memberRoleId = proxyMethodResult.get("memberRoleId")
        val memberEntity = proxy.findMemberById(memberId!!)
        val memberRoleEntity = proxy.findMemberRoleById(memberRoleId!!)
        assertThat(memberEntity).isNotNull
        assertThat(memberRoleEntity).isNotNull
    }
}
