package com.yojic.querydslstudy.repository

import com.yojic.querydslstudy.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRoleJpaRepository : JpaRepository<MemberRole, Long>
