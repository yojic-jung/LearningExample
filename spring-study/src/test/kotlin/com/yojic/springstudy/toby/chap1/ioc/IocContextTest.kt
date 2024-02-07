package com.yojic.springstudy.toby.chap1.ioc

import com.yojic.springstudy.toby.chap1beanfactory.hierarchy.ChildConfig
import com.yojic.springstudy.toby.chap1beanfactory.hierarchy.ParentConfig
import com.yojic.springstudy.toby.chap1.di.Patty
import com.yojic.springstudy.toby.chap1.di.Person
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.util.*

@SpringBootTest
class IocContextTest {
    @Autowired
    private lateinit var context: ApplicationContext

    @Test
    fun `스프링 IOC Context 구현체 확인`() {
        println(context::class.qualifiedName)
    }

    @Test
    fun `스프링 IOC Context 모든 빈 이름 조회`() {
        val beanNameArr = context.beanDefinitionNames
        for (beanName in beanNameArr) println(beanName)
    }

    @Test
    fun `스프링 IOC Context 빈 타입으로 접근`() {
        val beanNameArr = context.getBeansOfType(Patty::class.java)
        for (beanName in beanNameArr) println(beanName)
    }

    @Test
    fun `스프링 IOC Context 빈 이름으로 접근`() {
        val beefPatty = context.getBean("chickenPatty")
        println(beefPatty)
    }

    @Test
    fun `현재 사용중인 ApplicationContext 구현체 확인`() {
        val contextType = context::class.simpleName
        println(contextType)
    }

    @Test
    fun `BeanFactory 계층 구조 확인`() {
        // config 계층 설정
        val parentContext = AnnotationConfigApplicationContext(ParentConfig::class.java)
        val childContext = AnnotationConfigApplicationContext()
        childContext.parent = parentContext
        childContext.register(ChildConfig::class.java)
        childContext.refresh()

        val parent = childContext.getBean("parent", Person::class.java)
        val child = childContext.getBean("child", Person::class.java)
        val overrideTest = childContext.getBean("overrideTest", Person::class.java)

        println(childContext.beanFactory.parentBeanFactory === parentContext.beanFactory)
        println(parent.name)
        println(child.name)
        println(overrideTest.name)

        childContext.close()
        parentContext.close()
    }

    @Autowired
    lateinit var messageSource: MessageSource

    @Test
    fun `메시지 테스트`() {
        Locale.setDefault(Locale("en", "US"))
        println(messageSource.getMessage("name", arrayOf("tony"), Locale.getDefault()))
        println(messageSource.getMessage("name", arrayOf("토니"), Locale.KOREA))
    }
}
