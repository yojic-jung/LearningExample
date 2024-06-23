package com.yojic.springstudy.transaction.proxy.compile

import com.yojic.springstudy.transaction.proxy.sample.MemberDto
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition

/**
 * def. 컴파일 의존 프록시를 이용한 트랜잭션 처리
 * desc.
 * - 가장 기본적인 프록시 패턴
 * - 타깃과 같은 인터페이스 타입 구현 - 타깃 마다 새로운 프록시 생성해야함
 * - 타깃 클래스 타입을 명시적으로 프록시에 선언해야함(컴파일 의존)
 * - 메서드 마다 부가기능(트랜잭션) 코드 중복
 */
@Qualifier("compileProxy")
@Component
class MemberServiceCompileProxy(
    private val transactionManager: PlatformTransactionManager,
    private val memberServiceImpl: MemberService,
) : MemberService {
    override fun create(memberDto: MemberDto) {
        val status = transactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            memberServiceImpl.create(memberDto)
            transactionManager.commit(status)
        } catch (e: Exception) {
            transactionManager.rollback(status)
            throw e
        }
    }
}
