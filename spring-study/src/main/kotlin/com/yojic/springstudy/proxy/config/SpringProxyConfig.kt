package com.yojic.springstudy.proxy.config

import com.yojic.springstudy.proxy.transaction.invocation.TransactionAdvice
import com.yojic.springstudy.proxy.transaction.service.IMemberService
import com.yojic.springstudy.proxy.transaction.service.MemberServiceImpl
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class SpringProxyConfig