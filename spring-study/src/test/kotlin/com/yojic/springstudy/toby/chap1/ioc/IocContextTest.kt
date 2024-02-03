package com.yojic.springstudy.toby.chap1.ioc

import com.yojic.springstudy.beanfactory.hierarchy.ChildConfig
import com.yojic.springstudy.beanfactory.hierarchy.ChildConfigWithAnnotation
import com.yojic.springstudy.beanfactory.hierarchy.ParentConfig
import com.yojic.springstudy.toby.chap1.di.Patty
import com.yojic.springstudy.toby.chap1.di.Person
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootTest
class IocContextTest {
    @Autowired
    private lateinit var context: ApplicationContext
    @Autowired
    private lateinit var childConfig: ChildConfig
    @Autowired
    private lateinit var parentConfig: ParentConfig

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

    @Test
    fun `BeanFactory @Import 사용시 계층 구조 확인`() {
        val childContext = AnnotationConfigApplicationContext(ChildConfigWithAnnotation::class.java)

        val parentBean = childContext.getBean("parent", Person::class.java)
        val childBean = childContext.getBean("child", Person::class.java)
        val overrideTestBean = childContext.getBean("overrideTest", Person::class.java)

        println(parentBean.name)
        println(childBean.name)
        println(overrideTestBean.name)
    }

    @Test
    fun `Configuration 테스트`() {
        println(childConfig)
        println(parentConfig)
        println(childConfig === parentConfig)
    }
}
