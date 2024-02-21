package com.yojic.springstudy.proxy

import com.yojic.springstudy.proxy.concat.Hello
import com.yojic.springstudy.proxy.concat.HelloProxyFactory
import com.yojic.springstudy.proxy.config.DinamicProxyConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

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
}
