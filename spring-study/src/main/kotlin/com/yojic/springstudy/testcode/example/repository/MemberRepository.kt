package com.yojic.springstudy.testcode.example.repository

import com.yojic.springstudy.testcode.example.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<MemberEntity, Long>