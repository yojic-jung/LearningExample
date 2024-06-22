package com.yojic.springstudy.transaction.proxy.sample

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<MemberEntity, Long>
