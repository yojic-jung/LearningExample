package com.yojic.springstudy.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(com.yojic.springstudy.di.ParentConfigWithAnnotation::class)
class ChildConfigWithAnnotation {
    @Bean
    fun child() = com.yojic.springstudy.di.Person("child")

    @Bean
    fun overrideTest() = com.yojic.springstudy.di.Person("Yes, it is overridden")
}