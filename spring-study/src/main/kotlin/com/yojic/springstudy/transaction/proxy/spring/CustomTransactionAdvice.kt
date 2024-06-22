package com.yojic.springstudy.transaction.proxy.spring

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition

/**
 * Def. 스프링에서 트랜잭션 처리에 사용되는 프록시
 * Desc.
 * - 타깃 객체를 참조속성이 아닌 메서드 인자값으로 전달해주어 스프링 빈으로 등록 가능
 * - 메서드 선정 알고리즘(포인트컷)에 의존하지 않음, 오직 부가기능 제공만
 */
open class CustomTransactionAdvice : MethodInterceptor {

    override fun invoke(invocation: MethodInvocation): Any? {
        return Any()
    }
}
