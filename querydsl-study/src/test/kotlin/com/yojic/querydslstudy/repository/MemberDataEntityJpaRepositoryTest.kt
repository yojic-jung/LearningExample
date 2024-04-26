package com.yojic.querydslstudy.repository

import jakarta.transaction.Transactional
import org.hibernate.proxy.HibernateProxy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan

@SpringBootTest
@ComponentScan(basePackages = ["com.yojic.querydslstudy.config"])
class MemberDataEntityJpaRepositoryTest {
    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    lateinit var memberDataJpaRepository: MemberDataJpaRepository

    @Test
    @Transactional
    fun `class lazy laoding 테스트`() {
        val member = memberJpaRepository.findById(1)
        println("dd")
    }

    @Test
    fun `data class lazy laoding 테스트`() {
        val member = memberDataJpaRepository.findByMemId(1)
        println(member?.role is HibernateProxy)
        println(member?.role)
    }
}
