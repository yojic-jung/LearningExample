package com.yojic.querydslstudy.repository

import com.querydsl.core.NonUniqueResultException
import com.yojic.querydslstudy.entity.MemberEntity
import com.yojic.querydslstudy.entity.MemberRoleEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackages = ["com.yojic.querydslstudy.config"])
class MemberQueryRepositoryImplTest {
    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    lateinit var memberRoleJpaRepository: MemberRoleJpaRepository



    @Test
    fun `dto로 특정 칼럼 조회 테스트`() {
        memberJpaRepository.save(MemberEntity(memId = 1, email = "dywlr@naver.com"))

        val emailList = memberJpaRepository.findAllMembersEmail()
        assertThat(emailList?.get(0)?.email).isEqualTo("dywlr@naver.com")
    }

    @Test
    fun `fetchOne 테스트`() {
        // 0건 조회 - null 반환
        val nullExpected = memberJpaRepository.findMemberEmailOnlyOne()
        println(nullExpected)
        assertThat(nullExpected).isNull()

        // 1건 조회
        memberJpaRepository.save(MemberEntity(memId = 1, email = "dywlr@naver.com"))
        val member = memberJpaRepository.findMemberEmailOnlyOne()
        assertThat(member).isNotNull

        // 2건 조회 - NonUniqueResultException 발생
        memberJpaRepository.save(MemberEntity(memId = 2, email = "emailtest@email.com"))
        Assertions.assertThrows(NonUniqueResultException::class.java) {
            memberJpaRepository.findMemberEmailOnlyOne()
        }
    }

    @Test
    fun `fetchFirst 테스트`() {
        // 0건 조회 - null 반환
        val nullExpected = memberJpaRepository.findMemberEmailLimitOne()
        assertThat(nullExpected).isNull()

        // 1건 조회
        memberJpaRepository.save(MemberEntity(memId = 1, email = "dywlr@naver.com"))
        val member = memberJpaRepository.findMemberEmailLimitOne()
        assertThat(member).isNotNull

        // limit으로 오직 1건만 조회, NonUniqueResultException 발생 시키지 않음
        memberJpaRepository.save(MemberEntity(memId = 2, email = "emailtest@email.com"))
        val memberSerchedPlural = memberJpaRepository.findMemberEmailLimitOne()
        assertThat(memberSerchedPlural).isNotNull
    }



    @Test
    fun `fetchjoin 테스트`() {
        // given
        for (i in 1..10) {
            val mem = MemberEntity(email = "test $i")
            mem.roles = mutableListOf(MemberRoleEntity(memId = i.toLong(), enabled = true, roleName = "user"))
            memberJpaRepository.save(mem)
        }
        // when
        val memberListWithRole = memberJpaRepository.findAllMembersFetchJoin()
        // then
        for (mem in memberListWithRole!!) {
            println("${mem.memId}, ${mem.roles?.get(0)?.memRoleId}, ${mem.roles?.get(0)?.roleName}")
        }

        assertThat(memberListWithRole?.get(0)?.roles?.size).isGreaterThan(0)
    }

    @Test
    fun `no-fetchjoin 테스트`() {
        // given
        for (i in 0..10) {
            val mem = MemberEntity(email = "test $i")
            mem.roles = mutableListOf(MemberRoleEntity(memId = i.toLong(), enabled = true, roleName = "user"))
            memberJpaRepository.save(mem)
        }
        // when
        val memberListWithRole = memberJpaRepository.findAllMembersNoFetchJoin()
        // then
        assertThat(memberListWithRole?.get(0)?.roles?.size).isEqualTo(1)
    }
}
