//package com.yojic.querydslstudy.statistics.sum.repository
//
//import com.yojic.querydslstudy.entity.MemberEntity
//import jakarta.persistence.EntityManager
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//
//import org.junit.jupiter.api.BeforeEach
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.context.annotation.ComponentScan
//
//@DataJpaTest
//@ComponentScan(basePackages = [
//    "com.yojic.querydslstudy.config",
//    "com.yojic.querydslstudy.statistics",
//    "com.yojic.querydslstudy.entity"
//])
//class MemberEntitySumQueryRepositoryTest(
//    @Autowired
//    private val memberSumQueryRepository: MemberSumQueryRepository,
//    @Autowired
//    private val em: EntityManager
//) {
//    @BeforeEach
//    fun `테스트 데이터 초기화`() {
//        em.persist(MemberEntity())
//    }
//
//    @Test
//    fun `sum(case 조건 then 상수) 반환형 숫자`() {
//        // when
//        val cnt = memberSumQueryRepository.getSumValue()
//
//        // then
//        assertThat(cnt).isEqualTo(1)
//    }
//
//    @Test
//    fun `sum(case 조건 then 상수) 반환형 dto`() {
//        memberSumQueryRepository.getDtoFailCase()
//    }
//
//    @Test
//    fun getDtoList() {
//        memberSumQueryRepository.getDtoList()
//    }
//}
