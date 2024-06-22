package com.yojic.springstudy.transaction.proxy.spring

import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class SpringProxyConfig(
    private val txManager: PlatformTransactionManager,
) {
    @Bean
    @Qualifier("nameMatcher")
    fun nameMatcher(): AspectJExpressionPointcut {
        var aspectPointcut = AspectJExpressionPointcut()
        aspectPointcut.expression = "execution(* *(..))"
        return aspectPointcut
    }

    @Qualifier("txAdvice")
    @Bean
    fun customTransactionAdvice(): CustomTransactionAdvice {
        val customTxAdvice = CustomTransactionAdvice()
        return customTxAdvice
    }

//    @Bean
//    fun customTransactionAdvisor(): Advisor {
//        return DefaultPointcutAdvisor(nameMatcher(), customTransactionAdvice())
//    }
//
//    @Bean
//    fun myService(
//        customTransactionAdvisor: Advisor,
//        memberRepo: MemberRepository,
//        memberRoleRepo: MemberRoleRepository,
//        memberMapper: MemberMapper,
//    ): MemberService {
//        val proxyFactoryBean = ProxyFactoryBean()
//        proxyFactoryBean.setTarget(MemberServiceImpl(memberRepo, memberRoleRepo, memberMapper))
//        proxyFactoryBean.addAdvisor(customTransactionAdvisor)
//        return proxyFactoryBean.`object` as MemberService
//    }
}
