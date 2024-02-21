package com.yojic.springstudy.proxy.config

import com.yojic.springstudy.proxy.concat.Hello
import com.yojic.springstudy.proxy.concat.HelloImpl
import com.yojic.springstudy.proxy.concat.HelloProxyFactory
import com.yojic.springstudy.proxy.transaction.dao.MemberDao
import com.yojic.springstudy.proxy.transaction.dao.MemberRoleDao
import com.yojic.springstudy.proxy.transaction.factory.TxProxyFactoryBean
import com.yojic.springstudy.proxy.transaction.service.IMemberService
import com.yojic.springstudy.proxy.transaction.service.MemberServiceImpl
import jakarta.transaction.TransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class DinamicProxyConfig {
    @Bean
    fun hello() = HelloProxyFactory(HelloImpl(), Hello::class.java)

    @Bean
    fun memberServiceImpl(memberDao: MemberDao, memberRoleDao: MemberRoleDao) = MemberServiceImpl(memberDao, memberRoleDao)

    @Bean
    fun memberService(memberServiceImpl: IMemberService, txManager: PlatformTransactionManager) = TxProxyFactoryBean(memberServiceImpl, txManager, "register", IMemberService::class.java)
}