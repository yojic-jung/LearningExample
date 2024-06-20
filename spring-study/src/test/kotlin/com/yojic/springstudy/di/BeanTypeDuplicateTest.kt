package com.yojic.springstudy.di

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootTest
class BeanTypeDuplicateTest {
    @Test
    fun `NoSuchBeanDefinitionException 런타임 예외 확인`() {
        val ctx = AnnotationConfigApplicationContext(AppConfig::class.java)
//        assertThrows(NoSuchBeanDefinitionException::class.java) {
//            ctx.getBean(NoSuchBean::class.java)
//        }
        ctx.close()
    }

    @Test
    fun `BeanTypeDuplicate 런타임 예외 확인`() {
        val ctx = AnnotationConfigApplicationContext(AppConfig::class.java)
        assertThrows(NoUniqueBeanDefinitionException::class.java) {
            ctx.getBean(BeanTypeDuplicate::class.java)
        }
        ctx.close()
    }
}
