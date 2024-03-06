package com.yojic.querydslstudy.repository

import com.querydsl.core.NonUniqueResultException
import com.yojic.querydslstudy.entity.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
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

    @Autowired
    lateinit var memberQueryRepositoryImpl: MemberQueryRepositoryImpl

    @Test
    fun `dto로 특정 칼럼 조회 테스트`() {
        memberJpaRepository.save(Member(memId = 1, email = "dywlr@naver.com"))

        val emailList = memberQueryRepositoryImpl.findAllMembersEmail()
        assertThat(emailList?.get(0)?.email).isEqualTo("dywlr@naver.com")
    }

    @Test
    fun `fetchOne 테스트`() {
        // 0건 조회 - null 반환
        val nullExpected = memberJpaRepository.findMemberEmailOnlyOne()
        println(nullExpected)
        assertThat(nullExpected).isNull()

        // 1건 조회
        memberJpaRepository.save(Member(memId = 1, email = "dywlr@naver.com"))
        val member = memberQueryRepositoryImpl.findMemberEmailOnlyOne()
        assertThat(member).isNotNull

        // 2건 조회 - NonUniqueResultException 발생
        memberJpaRepository.save(Member(memId = 2, email = "emailtest@email.com"))
        Assertions.assertThrows(NonUniqueResultException::class.java) {
            memberQueryRepositoryImpl.findMemberEmailOnlyOne()
        }
    }

    @Test
    fun `fetchFirst 테스트`() {
        // 0건 조회 - null 반환
        val nullExpected = memberJpaRepository.findMemberEmailLimitOne()
        assertThat(nullExpected).isNull()

        // 1건 조회
        memberJpaRepository.save(Member(memId = 1, email = "dywlr@naver.com"))
        val member = memberJpaRepository.findMemberEmailLimitOne()
        assertThat(member).isNotNull

        // limit으로 오직 1건만 조회, NonUniqueResultException 발생 시키지 않음
        memberJpaRepository.save(Member(memId = 2, email = "emailtest@email.com"))
        val memberSerchedPlural = memberQueryRepositoryImpl.findMemberEmailLimitOne()
        assertThat(memberSerchedPlural).isNotNull
    }

    /*
        @Test
        fun `fetchjoin 테스트`() {
            // given

            // when


            // then
            assertThat(memberListWithRole?.get(0)?.roles?.size).isGreaterThan(0)
        }

        @Test
        fun `no-fetchjoin 테스트`() {
            // given
            for (i in 0..10) {
                val mem = Member(email = "test $i")
                val roles = mutableListOf(MemberRole(memId = i.toLong(), enabled = true, roleName = "user"))
                var regMem = memberJpaRepository.save(mem)
                for (role in roles) {
                    role.memId = regMem.memId
                    val regRole = memberRoleJpaRepository.save(role)
                }
            }
            // when
            val memberListWithRole = memberQueryRepositoryImpl.findAllMembersNoFetchJoin()
            // then
            for (mem in memberListWithRole!!) {
                println("${mem.memId}, ${mem.roles?.get(0)?.memRoleId}, ${mem.roles?.get(0)?.roleName}")
            }
            val roles = memberRoleJpaRepository.findAll()
            for (role in roles) {
                println(role.roleName)
            }
            assertThat(memberListWithRole?.get(0)?.roles?.size).isEqualTo(1)
        }
        */
}
