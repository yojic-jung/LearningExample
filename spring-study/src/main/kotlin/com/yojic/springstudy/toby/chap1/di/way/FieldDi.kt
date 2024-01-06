package com.yojic.springstudy.toby.chap1.di.way

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FieldDi {
    @Autowired
    lateinit var apple: Apple
    @Autowired
    lateinit var banana: Banana

    fun diWorkingMethod() {
        println("${apple.appleMethod()}, ${banana.bananaMethod()}")
    }
}
