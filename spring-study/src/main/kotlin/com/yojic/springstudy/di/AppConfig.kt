package com.yojic.springstudy.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.yojic.springstudy.di"])
class AppConfig {
    @Bean
    fun beanNameDuplicate(): BeanNameDuplicateRegConfig {
        return BeanNameDuplicateRegConfig()
    }

    @Bean
    fun beefPatty(): BeefPatty {
        return BeefPatty()
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
}
