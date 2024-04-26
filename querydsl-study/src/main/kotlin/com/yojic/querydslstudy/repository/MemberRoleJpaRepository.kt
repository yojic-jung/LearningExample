package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberRoleDataEntity
import com.yojic.querydslstudy.entity.MemberRoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRoleJpaRepository : JpaRepository<MemberRoleEntity, Long> {
    fun findByMemRoleId(id: Long): MemberRoleEntity?
}
