package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberDataEntity
import com.yojic.querydslstudy.entity.MemberEntity
import com.yojic.querydslstudy.entity.MemberRoleDataEntity
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.proxy.HibernateProxy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackages = ["com.yojic.querydslstudy.config"])
class MemberEntityJpaRepositoryTest {
    @Autowired
    lateinit var memberJpaRepository: MemberDataJpaRepository

    @Autowired
    lateinit var memberRoleJpaRepository: MemberRoleDataJpaRepository

    @Test
    fun `JPA, Querydsl 리포지토리 상속 테스트`() {
        memberJpaRepository.save(MemberDataEntity())
        val memberEntity: List<MemberEntity>? = memberJpaRepository.findAllMembers()
        assertThat(memberEntity?.size ?: 0).isEqualTo(1)
    }

    @Test
    fun `JPA lazy loading 영속성 컨텍스트 동작 원리`() {
        // 앤티티 저장 및 연관관계 설정
        val mem = MemberDataEntity()
        val regMem = memberJpaRepository.save(mem)
        val role = MemberRoleDataEntity(memId = regMem.memId, roleName = "user", enabled = true)
        val regMemRole = memberRoleJpaRepository.save(role)
        // 1차캐시에 엔티티의 변경이 반영됨, 1차 캐시 엔티티와 스냅샷을 통해 변경 감지
        regMem.role = mutableListOf(regMemRole)
        // 엔티티를 조회해 올 때, 변경이 일어난 엔티티라면 변경사항을(update 쿼리 실행) DB에 반영하고(flush)
        // 다시 조회해와서 1차 캐시에 저장, 스냅샷도 이 시점에 다시 찍음
        val memberEntity: MemberDataEntity? = memberJpaRepository.findByMemId(1)

        // 1차캐시에 엔티티가 존재하므로 프록시 아님
        assertThat(memberEntity?.role is HibernateProxy).isFalse()
    }
}
