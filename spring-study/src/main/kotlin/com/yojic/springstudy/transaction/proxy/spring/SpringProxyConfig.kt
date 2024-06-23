package com.yojic.springstudy.transaction.proxy.spring

import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Primary
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class SpringProxyConfig(
    private val txManager: PlatformTransactionManager,
) {
    @Bean
    @Qualifier("nameMatcher")
    fun nameMatcher(): AspectJExpressionPointcut {
        // 프록시의 타깃은 final이 선언되면 안됨. 따라서 타깃 범위를 최대한 줄여놨음
        // 너무 지나치게 범용적으로 포인트컷을 걸면 해당 클래스 다 open 처리해야함
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

    @Bean
    @Qualifier("memberServiceProxy")
    fun memberService(): ProxyFactoryBean {
        val proxyFactoryBean = ProxyFactoryBean()
        proxyFactoryBean.setTargetName("memberServiceImpl")
        proxyFactoryBean.setInterceptorNames("customTransactionAdvisor")
        return proxyFactoryBean
    }
}
