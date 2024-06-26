package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberDataEntity
import com.yojic.querydslstudy.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<MemberEntity, Long>, MemberQueryRepository {

    fun findByMemId(memId: Long): MemberEntity?

    @Query("SELECT m FROM MemberEntity m")
    fun findAllOnlyMember(): List<MemberEntity>?

    @Query("SELECT m FROM MemberEntity m LEFT JOIN FETCH m.role")
    fun findAllWithRoles(): List<MemberEntity>?
}
