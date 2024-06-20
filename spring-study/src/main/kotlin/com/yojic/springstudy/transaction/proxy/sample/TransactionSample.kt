package com.yojic.springstudy.transaction.proxy.sample

import com.yojic.springstudy.transaction.proxy.compile.Member
import com.yojic.springstudy.transaction.proxy.compile.MemberRepository
import com.yojic.springstudy.transaction.proxy.compile.MemberRole
import com.yojic.springstudy.transaction.proxy.compile.MemberService
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition

@Component
class TransactionSample(
    private val transactionManager: PlatformTransactionManager,
    private val memberRepo: MemberRepository,
) : MemberService {
    override fun create(member: Member) {
        val status = transactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            val id = memberRepo.create(member)
            memberRepo.addRole(MemberRole(id, "USER"))
            transactionManager.commit(status)
        } catch (e: Exception) {
            transactionManager.rollback(status)
            throw e
        }
    }
}
