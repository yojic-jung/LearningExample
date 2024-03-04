package com.yojic.springstudy.testcode.repository

import com.yojic.springstudy.testcode.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<MemberEntity, Long>