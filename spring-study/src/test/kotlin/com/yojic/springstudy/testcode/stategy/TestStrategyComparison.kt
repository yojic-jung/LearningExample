package com.yojic.springstudy.testcode.stategy

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

/**
 *  Def : 테스트 종류별 특징 비교
 */

@WebMvcTest
class WebMvcTest(
    @Autowired
    private val iocContext: ApplicationContext
) {
    @Test
    fun `@WebMvcTest시 컨텍스가 띄우는 빈 갯수 및 목록`() {
        // 내가 정의한 스프링 빈 제외
        val beanNameArr = iocContext.beanDefinitionNames.filter { beanName ->
            iocContext.getType(beanName)?.packageName?.startsWith("com.yojic") == false
        }
        // 빈 갯수 및 스프링에서 제공하는 빈 이름
        println(beanNameArr.size)
        for (beanName in beanNameArr) println(beanName)
    }
}

@SpringBootTest
class SpringBootTest(
    @Autowired
    private val iocContext: ApplicationContext
) {
    @Test
    fun `@SpringBootTest시 컨텍스트가 띄우는 빈 갯수 및 목록`() {
        // 내가 정의한 스프링 빈 제외
        val beanNameArr = iocContext.beanDefinitionNames.filter { beanName ->
            iocContext.getType(beanName)?.packageName?.startsWith("com.yojic") == false
        }
        // 빈 갯수 및 스프링에서 제공하는 빈 이름
        println(beanNameArr.size)
        for (beanName in beanNameArr) println(beanName)
    }
}