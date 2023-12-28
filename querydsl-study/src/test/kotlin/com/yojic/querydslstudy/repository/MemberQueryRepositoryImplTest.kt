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

    @BeforeEach
    fun test() {
        // given
        val memberList = ArrayList<MemberEntity>()
        for (i in 0..50) {
            val mem = MemberEntity(memId = i.toLong())
            val role = MemberRoleEntity(memId = i.toLong(), enabled = true, roleName = "user")
            mem.roles = mutableListOf<MemberRoleEntity>(role)
            memberList.add(mem)
        }
        memberJpaRepository.saveAll(memberList)
    }

    @Test
    fun `fetchjoin 테스트`() {
        val memberListWithRole = memberJpaRepository.findAllMembersFetchJoin()
        assertThat(memberListWithRole?.get(0)?.roles?.get(0)).isNotNull
    }
}
