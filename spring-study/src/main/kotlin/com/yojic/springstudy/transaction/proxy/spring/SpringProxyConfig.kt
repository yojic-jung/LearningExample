package com.yojic.springstudy.transaction.proxy.spring

import com.yojic.springstudy.transaction.proxy.compile.MemberService
import org.aopalliance.intercept.MethodInterceptor
import org.springframework.aop.Advisor
import org.springframework.aop.Pointcut
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.aop.support.DefaultPointcutAdvisor
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
    fun nameMatcher(): Pointcut {
        // 프록시의 타깃은 final이 선언되면 안됨. 따라서 타깃 범위를 최대한 줄여놨음
        // 너무 지나치게 범용적으로 포인트컷을 걸면 해당 클래스 다 open 처리해야함
        // expression 잘못 설정하면 프록시 빈 등록 정상적이지 않음
        var aspectPointcut = AspectJExpressionPointcut()
        aspectPointcut.expression = "execution(public void com.yojic.springstudy.transaction.proxy.compile" +
            ".MemberServiceImpl" +
            ".create" +
            "(com.yojic.springstudy.transaction.proxy.sample.MemberDto))"
        return aspectPointcut
    }

    @Qualifier("txAdvice")
    @Bean
    fun customTransactionAdvice(): MethodInterceptor {
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

        // 인터페이스 설정 없으면 프록시 설정 정상적이지 않음
        proxyFactoryBean.setProxyInterfaces(arrayOf(MemberService::class.java))
        return proxyFactoryBean
    }

//    /**
//     * DefaultAdvisorAutoProxyCreator를 사용하려면 기존에 등록된 프록시는 모두 주석 처리해야함
//     * memberService(): ProxyFactoryBean와 MemberServiceCompileProxy 주석 처리해야함
//     * 그렇지 않으면 같은 인터페이스 타입에 여러 구현체 존재하여 스프링이 어떤 빈을 의존 주입할지 모름
//     * 테스트시 MemberServiceSpringProxyTest에서 Qualifier 제거하여 테스트 가능
//     */
//    @Bean
//    fun defaultAdvisorAutoProxyCreator(): DefaultAdvisorAutoProxyCreator {
//        return DefaultAdvisorAutoProxyCreator()
//    }
}
