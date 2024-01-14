package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<Member, Long>, MemberQueryRepository {

    fun findByMemId(memId: Long): Member?

    @Query("SELECT m FROM Member m")
    fun findAllOnlyMember(): List<Member>?

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.role")
    fun findAllWithRoles(): List<Member>?
}
