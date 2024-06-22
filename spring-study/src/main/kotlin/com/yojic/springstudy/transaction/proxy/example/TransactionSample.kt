package com.yojic.springstudy.transaction.proxy.example

import com.yojic.springstudy.transaction.proxy.compile.MemberService
import com.yojic.springstudy.transaction.proxy.sample.MemberDto
import com.yojic.springstudy.transaction.proxy.sample.MemberMapper
import com.yojic.springstudy.transaction.proxy.sample.MemberRoleRepository
import com.yojic.springstudy.transaction.proxy.sample.MemberRepository
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition

class TransactionSample(
    private val transactionManager: PlatformTransactionManager,
    private val memberMapper: MemberMapper,
    private val memberRepo: MemberRepository,
    private val memberRoleRepo: MemberRoleRepository,
) : MemberService {
    override fun create(memberDto: MemberDto) {
        val status = transactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            val memberEntity = memberRepo.save(memberMapper.memberToEntity(memberDto))
            memberRoleRepo.save(memberMapper.roleToEntity(memberEntity, "USER"))
            transactionManager.commit(status)
        } catch (e: Exception) {
            transactionManager.rollback(status)
            throw e
        }
    }
}
