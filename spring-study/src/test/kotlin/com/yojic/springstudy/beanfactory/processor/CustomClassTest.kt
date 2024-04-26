package com.yojic.springstudy.beanfactory.processor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class CustomClassTest(
    @Autowired private val context: ApplicationContext
){
    @Test
    fun `customBean 등록 확인`(){
        val customClass = context.getBean("customClass")
        assertThat(customClass).isNotNull
    }
}
