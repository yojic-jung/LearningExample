package com.yojic.springstudy.proxy.config

import com.yojic.springstudy.proxy.concat.Hello
import com.yojic.springstudy.proxy.concat.HelloImpl
import com.yojic.springstudy.proxy.concat.HelloProxyFactory
import com.yojic.springstudy.proxy.transaction.dao.MemberDao
import com.yojic.springstudy.proxy.transaction.dao.MemberRoleDao
import com.yojic.springstudy.proxy.transaction.factory.TxProxyFactoryBean
import com.yojic.springstudy.proxy.transaction.invocation.TransactionAdvice
import com.yojic.springstudy.proxy.transaction.service.IMemberService
import com.yojic.springstudy.proxy.transaction.service.MemberServiceImpl
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class DinamicProxyConfig {
    @Bean
    fun hello() = HelloProxyFactory(HelloImpl(), Hello::class.java)

    @Qualifier("default")
    @Bean
    fun memberServiceImpl(memberDao: MemberDao, memberRoleDao: MemberRoleDao) =
            MemberServiceImpl(memberDao, memberRoleDao)

    @Qualifier("dinamicProxy")
    @Bean
    fun memberService(memberServiceImpl: IMemberService, txManager: PlatformTransactionManager) =
            TxProxyFactoryBean(memberServiceImpl, txManager, "register", IMemberService::class.java)

    @Bean
    fun transactionPointcut(): Pointcut {
        var pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("register*")
        return pointcut
    }

    @Qualifier("default")
    @Bean
    fun transactionAdvice(transactionManager: PlatformTransactionManager) = TransactionAdvice(transactionManager)

    @Bean
    fun transactionAdvisor(transactionPointcut: Pointcut, transactionAdvice: TransactionAdvice) =
            DefaultPointcutAdvisor(transactionPointcut, transactionAdvice)

//    @Qualifier("springProxy")
//    @Bean
//    fun memberServiceSpringProxy(
//            memberServiceImpl: MemberServiceImpl,
//            transactionAdvice: TransactionAdvice
//    ): ProxyFactoryBean {
//        val proxyFactory = ProxyFactoryBean()
//        proxyFactory.setTarget(memberServiceImpl)
//        proxyFactory.setInterceptorNames("transactionAdvice")
//        return proxyFactory
//    }

}