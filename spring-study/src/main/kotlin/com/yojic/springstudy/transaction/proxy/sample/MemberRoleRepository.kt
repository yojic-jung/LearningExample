package com.yojic.springstudy.transaction.proxy.sample

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRoleRepository : JpaRepository<MemberRoleEntity, Long>
