package com.yojic.springstudy.toby.chap1.di

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