package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<MemberEntity, Long>, MemberQueryRepository {

    fun findByMemId(memId: Long): MemberEntity?

    @Query("SELECT m FROM Member m")
    fun findAllOnlyMember(): List<MemberEntity>?

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.roles")
    fun findAllWithRoles(): List<MemberEntity>?
}
