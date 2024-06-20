package com.yojic.springstudy.transaction.reflection.transaction.invocation

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition
import java.lang.reflect.InvocationTargetException

/**
 * InvocationHandler와 달리 스프링 빈 등록 가능함
 * InvocationHandler는 타깃 정보를 가지고 있음
 * 타깃과 타깃의 메서드 패턴에 종속되기 때문에 여러 프로시에서 공유하기 어려움
 * 반면 MethodInterceptor는 타깃 정보 없으니 스프링 빈 등록 가능
 * 메서드 패턴은 포인트컷 객체에서 제공
 * 객체와 그 객체의 메서드 패턴에 종속이라는 구조에서 벗어날 수 있음 -> 공유자원으로 사용가능(스프링 빈 등록 가능)
 */
@Component
class TransactionAdvice(
    private val transactionManager: PlatformTransactionManager,
) : MethodInterceptor {
    override fun invoke(invocation: MethodInvocation): Any? {
        val status = transactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            val returnVal = invocation.proceed()
            transactionManager.commit(status)
            return returnVal
        } catch (e: InvocationTargetException) {
            transactionManager.rollback(status)
            throw e.targetException
        } catch (e: RuntimeException) {
            transactionManager.rollback(status)
            throw e
        }
    }
}
