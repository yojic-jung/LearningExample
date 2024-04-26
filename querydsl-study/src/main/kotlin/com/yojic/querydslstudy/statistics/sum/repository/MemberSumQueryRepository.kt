package com.yojic.querydslstudy.statistics.sum.repository

import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yojic.querydslstudy.statistics.sum.dto.MemberStaticsDto
import com.yojic.querydslstudy.entity.QMemberEntity.memberEntity as member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Def : case when 과 sum을 함께 쓸때 then에 상수 적용방법
 */
@Repository
class MemberSumQueryRepository(
    private val queryFactory: JPAQueryFactory,
) {
    // dto로 받지 않고 숫자로 받는 경우
    fun getSumValue(): Long {
        return queryFactory.select(
            Expressions.cases().`when`(Expressions.TRUE)
                .then(1L).otherwise(0L).sum(),
        ).from(member).fetchFirst()
    }

    // dto로 받지만 case구문의 then에 상수값 박힌 경우(실패 케이스)
    fun getDtoFailCase(): MemberStaticsDto? {
        return queryFactory.select(
            Projections.constructor(
                MemberStaticsDto::class.java,
                Expressions.cases().`when`(Expressions.TRUE)
                    .then(1L).otherwise(0L).sum(),
            ),
        ).from(member).fetchOne()
    }

    // dto로 받지만 case구문의 then에 타입정보가 함께 포함된 상수값 박힌 경우
    fun getDto(): MemberStaticsDto? {
        return queryFactory.select(
            Projections.constructor(
                MemberStaticsDto::class.java,
                Expressions.cases().`when`(Expressions.TRUE)
                    .then(Expressions.asNumber(1L).castToNum(Long::class.java)).otherwise(0L).sum(),
            ),
        ).from(member).fetchOne()
    }

    // dto로 받지만 case구문의 then에 타입정보가 함께 포함된 상수값 박힌 경우
    fun getDtoList() = queryFactory.select(
        Expressions.cases().`when`(Expressions.TRUE).then(1L)
            .otherwise(0L).sum(),
    ).from(member).fetch()

}
