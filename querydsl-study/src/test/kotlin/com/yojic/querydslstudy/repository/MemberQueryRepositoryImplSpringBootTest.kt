package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.Member
import com.yojic.querydslstudy.entity.MemberRole
import com.yojic.querydslstudy.helper.TransactionHelper
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.proxy.HibernateProxy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberQueryRepositoryImplSpringBootTest(
    @Autowired var memberJpaRepository: MemberJpaRepository,
    @Autowired var memberRoleJpaRepository: MemberRoleJpaRepository,
    @Autowired var memberQueryRepositoryImpl: MemberQueryRepositoryImpl,
) {

    var regMemId: Long = 0

    @BeforeEach
    fun `데이터 셋업`() {
        val mem = Member(email = "test@test.com")
        val regMem = memberJpaRepository.save(mem)
        val roles = mutableListOf(
            MemberRole(memId = regMem.memId, enabled = true, roleName = "user"),
            MemberRole(memId = regMem.memId, enabled = true, roleName = "admin"),
        )
        memberRoleJpaRepository.saveAll(roles)

        regMemId = regMem.memId
    }

    @Test
    fun `fetchjoin 테스트 - lazy loading 동작 안함, 한방쿼리`() {
        val memberEntity = memberQueryRepositoryImpl.findMembersByMemIdFetchJoin(regMemId)!!
        // fetchjoin으로 연관관계까지 한번에 가져오므로 프록시가 아님
        assertThat(memberEntity is HibernateProxy).isFalse()
        assertThat(memberEntity.role is HibernateProxy).isFalse()
        assertThat(memberEntity.role!![0].memRoleId).isGreaterThan(0)

        memberEntity.role?.forEach {
            println("memId : ${it.memId},memRoleId : ${it.memRoleId}, roleName : ${it.roleName}")
        }
    }

    @Test
    @Transactional
    fun `noFetchjoin 테스트 - lazy loading 동작`() {
        val memberEntity = memberQueryRepositoryImpl.findMembersByMemIdNoFetchJoin(regMemId)!!
        memberEntity.role?.forEach { role ->
            println(role.roleName)
        }
        // fetchjoin으로 연관관계까지 한번에 가져오므로 프록시가 아님
        assertThat(memberEntity is HibernateProxy).isFalse()
        assertThat(memberEntity.role is HibernateProxy).isTrue()
    }

    @Test
    @Transactional
    fun `lazy loading 동작`() {
        val memberEntity = memberJpaRepository.findByMemId(regMemId)!!
        // fetchjoin으로 연관관계까지 한번에 가져오므로 프록시가 아님
        assertThat(memberEntity is HibernateProxy).isFalse()
        memberEntity.role?.size
        println(memberEntity.role?.size)
        assertThat(memberEntity.role is HibernateProxy).isTrue()
        assertThat(memberEntity.role).isNotNull()
    }
}
