package com.yojic.springstudy.proxy

import com.yojic.springstudy.proxy.concat.Hello
import com.yojic.springstudy.proxy.concat.HelloImpl
import com.yojic.springstudy.proxy.concat.HelloProxyFactory
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HelloTest(
        @Autowired
        private val hello: HelloProxyFactory,
) {
    @Test
    fun `Hello 프록시 테스트`() {
        val name = "John"
        val proxy = hello.getObject() as Hello
        val proxyMethodResult = proxy.hello(name)

        assertThat(proxyMethodResult).isEqualTo("$name, Hello")
    }

    @Test
    fun `스프링 proxyFactoryBean 테스트`() {
        // given
        val name = "Tom"
        var pfBean = ProxyFactoryBean()
        pfBean.setTarget(HelloImpl())
        pfBean.addAdvice(HelloConcatAdvice)
        val helloProxy = pfBean.`object` as Hello

        // when
        val result = helloProxy.hello(name)

        // then
        assertThat(result).isEqualTo("$name, Hello")
    }

    @Test
    fun `스프링 프록시 팩토리빈 pointcut과 Advisor의 분리`() {
        val name = "Tom"
        val pfBean = ProxyFactoryBean()
        pfBean.setTarget(HelloImpl())

        val pointCut = NameMatchMethodPointcut()
        pointCut.setMappedNames("hel*")

        // 포인트컷과 어드바이스의 조합(어드바이저)
        pfBean.addAdvisors(DefaultPointcutAdvisor(pointCut, HelloConcatAdvice))

        val helloProxy = pfBean.`object` as Hello

        assertThat(helloProxy.hello(name)).isEqualTo("$name, Hello")
        assertThat(helloProxy.hi(name)).isNotEqualTo("$name, Hello")
    }

}

object HelloConcatAdvice : MethodInterceptor {

    /**
     * InvocationHandler(TransactionHandler)와 달리 타깃 정보가 없음
     * MethodInvocation은 일종의 콜백 객체
     */
    override fun invoke(invocation: MethodInvocation): Any =
            "${invocation.proceed()}, Hello"
}