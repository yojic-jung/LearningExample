package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {

    @Query("SELECT m FROM Member m")
    fun findAllMembers(): List<MemberEntity>

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.roles")
    fun findAllMembersWithRoles(): List<MemberEntity>
}
