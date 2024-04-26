package com.yojic.springstudy.beanfactory.processor.proxy

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class SampleTargetTest(
    @Autowired private val context: ApplicationContext
) {
    @Test
    fun `프록시 객체 싱글톤레지스트리에 저장 확인`() {
        val testTarget = context.getBean("testTarget")
        Assertions.assertThat(AopUtils.isAopProxy(testTarget)).isTrue()
    }
}
