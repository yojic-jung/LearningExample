package com.yojic.springstudy.transaction.proxy.compile

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition

/**
 * def. 프록시를 이용한 트랜잭션 처리
 * desc.
 * - 가장 기본적인 프록시 패턴(컴파일 의존)
 * - 타깃과 같은 인터페이스 타입 구현 - 타깃 마다 새로운 프록시 생성해야함
 * - 타깃을 알고 있어야하는 단점
 * - 메서드 마다 부가기능(트랜잭션) 코드 중복
 */
class MemberServiceProxy(
    private val transactionManager: PlatformTransactionManager,
    private val memberServiceImpl: MemberService,
) : MemberService {
    override fun create(member: Member) {
        val status = transactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            memberServiceImpl.create(member)
            transactionManager.commit(status)
        } catch (e: Exception) {
            transactionManager.rollback(status)
            throw e
        }
    }
}
