package com.yojic.springstudy.beanfactory.hierarchy

import com.yojic.springstudy.toby.chap1.di.Person
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ParentConfigWithAnnotation::class)
class ChildConfigWithAnnotation {
    @Bean
    fun child() = Person("child")

    @Bean
    fun overrideTest() = Person("Yes, it is overridden")
}