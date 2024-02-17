package com.yojic.springstudy.di.way

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SetterDi {
    lateinit var apple: Apple
    lateinit var banana: Banana

    @Autowired
    @JvmName("setAppleFromKotlin")
    private fun setApple(newApple: Apple) {
        apple = newApple
    }

    @Autowired
    @JvmName("setBananaFromKotlin")
    private fun setBanana(banana: Banana) {
        this.banana = banana
    }
}
