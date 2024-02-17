package com.yojic.springstudy.beanfactory.hierarchy

import com.yojic.springstudy.di.Person
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ParentConfig {
    @Bean
    fun parent() = Person("parent")

    @Bean
    fun overrideTest() = Person("is overridden?")
}
