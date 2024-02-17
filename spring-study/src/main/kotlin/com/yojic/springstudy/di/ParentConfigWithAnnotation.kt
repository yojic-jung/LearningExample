package com.yojic.springstudy.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ParentConfigWithAnnotation {
    @Bean
    fun parent() = Person("parent")

    @Bean
    fun overrideTest() = Person("is overridden?")
}