package com.yojic.springstudy.toby.chap1.di.way

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FieldDi {
    @Autowired
    private lateinit var apple: Apple

    @Autowired
    private lateinit var banana: Banana

    fun diTest() {
        println("$apple, $banana")
    }
}
