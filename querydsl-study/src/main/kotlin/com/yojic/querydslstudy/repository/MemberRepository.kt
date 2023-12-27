package com.yojic.querydslstudy.repository

import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : MemberJpaRepository, MemberQueryRepository
