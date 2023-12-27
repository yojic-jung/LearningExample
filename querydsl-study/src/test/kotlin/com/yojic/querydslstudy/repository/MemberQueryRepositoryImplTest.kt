package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = ["com.yojic.querydslstudy.config"])
class MemberQueryRepositoryImplTest {
    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Test
    fun tests() {
        memberJpaRepository.save(MemberEntity())
        memberJpaRepository.findAllMembers()
    }
}