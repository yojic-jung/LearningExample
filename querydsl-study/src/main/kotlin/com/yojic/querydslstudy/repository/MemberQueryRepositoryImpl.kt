package com.yojic.querydslstudy.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MemberQueryRepositoryImpl(
    val jpaQueryFactory: JPAQueryFactory,
)
