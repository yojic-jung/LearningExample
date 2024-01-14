package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.Member
import com.yojic.querydslstudy.entity.MemberRole
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.Hibernate
import org.hibernate.proxy.HibernateProxy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import java.util.*

@DataJpaTest
@ComponentScan(basePackages = ["com.yojic.querydslstudy.config"])
class MemberJpaRepositoryTest {
    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    lateinit var memberRoleJpaRepository: MemberRoleJpaRepository

    @Test
    fun `JPA, Querydsl 리포지토리 상속 테스트`() {
        memberJpaRepository.save(Member())
        val member: List<Member>? = memberJpaRepository.findAllMembers()
        assertThat(member?.size ?: 0).isEqualTo(1)
    }

    @Test
    fun `JPA lazy loading 영속성 컨텍스트 동작 원리`() {
        // 앤티티 저장 및 연관관계 설정
        val mem = Member()
        val regMem = memberJpaRepository.save(mem)
        val role = MemberRole(memId = regMem.memId, roleName = "user", enabled = true)
        role.member = regMem
        val regMemRole = memberRoleJpaRepository.save(role)
        // 1차캐시에 엔티티의 변경이 반영됨, 1차 캐시 엔티티와 스냅샷을 통해 변경 감지
        regMem.role = mutableListOf(regMemRole)
        // 엔티티를 조회해 올 때, 변경이 일어난 엔티티라면 변경사항을(update 쿼리 실행) DB에 반영하고(flush)
        // 다시 조회해와서 1차 캐시에 저장, 스냅샷도 이 시점에 다시 찍음
        val member: Member? = memberJpaRepository.findByMemId(1)

        // 1차캐시에 엔티티가 존재하므로 프록시 아님
        assertThat(member is HibernateProxy).isFalse()
        assertThat(member?.role is HibernateProxy).isFalse()
    }
}
