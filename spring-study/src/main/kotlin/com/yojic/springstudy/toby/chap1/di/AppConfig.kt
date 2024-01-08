package com.yojic.springstudy.toby.chap1.di

import com.yojic.springstudy.toby.chap1.soc.BeefPatty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.yojic.springstudy.toby.chap1.di"])
class AppConfig {

    @Bean
    fun beanNameDuplicate(): BeanNameDuplicateRegConfig {
        return BeanNameDuplicateRegConfig()
    }

    /*
    @Bean
    fun beefPatty(): com.yojic.springstudy.toby.chap1.soc.BeefPatty {
        return com.yojic.springstudy.toby.chap1.soc.BeefPatty()
    }

    @Bean
    fun chickenPatty(): ChickenPatty {
        return ChickenPatty()
    }

    @Bean
    fun porkPatty(): PorkPatty {
        return PorkPatty()
    }

    @Bean
    fun hamburger(): Hamburger {
        return Hamburger(beefPatty())
    }
    s
     */
}
