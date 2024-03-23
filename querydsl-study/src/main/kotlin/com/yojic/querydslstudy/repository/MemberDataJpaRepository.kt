package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberDataEntity
import com.yojic.querydslstudy.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberDataJpaRepository : JpaRepository<MemberDataEntity, Long>, MemberQueryRepository {

    fun findByMemId(memId: Long): MemberDataEntity?

    @Query("SELECT m FROM MemberDataEntity m")
    fun findAllOnlyMember(): List<MemberEntity>?

    @Query("SELECT m FROM MemberDataEntity m LEFT JOIN FETCH m.role")
    fun findAllWithRoles(): List<MemberEntity>?
}
