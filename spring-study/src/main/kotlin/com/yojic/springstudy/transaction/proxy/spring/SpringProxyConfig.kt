package com.yojic.springstudy.transaction.proxy.spring

import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class SpringProxyConfig(
    private val txManager: PlatformTransactionManager,
) {
    @Bean
    @Qualifier("nameMatcher")
    fun nameMatcher(): AspectJExpressionPointcut {
        var aspectPointcut = AspectJExpressionPointcut()
        aspectPointcut.expression = "execution(public void com.yojic.springstudy.transaction.proxy.compile" +
            ".MemberServiceImpl" +
            ".create" +
            "(String)) && args(data)"
        return aspectPointcut
    }

    @Qualifier("txAdvice")
    @Bean
    fun customTransactionAdvice(): CustomTransactionAdvice {
        val customTxAdvice = CustomTransactionAdvice(txManager)
        return customTxAdvice
    }

    @Bean
    fun customTransactionAdvisor(): Advisor {
        return DefaultPointcutAdvisor(nameMatcher(), customTransactionAdvice())
    }
}
