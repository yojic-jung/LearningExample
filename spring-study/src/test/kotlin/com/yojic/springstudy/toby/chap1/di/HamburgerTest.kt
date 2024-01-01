package com.yojic.springstudy.toby.chap1.di

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HamburgerTest(
    @Autowired
    val hamburger: Hamburger,
) {
    @Test
    fun test() {
        print(hamburger.hamburgerPrice())
    }
}
