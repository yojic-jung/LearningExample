package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = ["com.yojic.querydslstudy.config"])
class MemberJpaRepositoryTest {
    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Test
    fun `JPA, Querydsl 리포지토리 상속 테스트`() {
        memberJpaRepository.save(MemberEntity())
        var memberEntity: List<MemberEntity>? = memberJpaRepository.findAllMembers()
        assertThat(memberEntity?.size ?: 0).isEqualTo(1)
    }
}