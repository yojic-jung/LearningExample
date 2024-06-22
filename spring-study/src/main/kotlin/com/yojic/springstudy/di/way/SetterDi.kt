package com.yojic.springstudy.di.way

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SetterDi {
    lateinit var apple: Apple
    lateinit var banana: Banana

    @Autowired
    @JvmName("setAppleFromKotlin") // 코틀린은 set형식의 메서드명이 없기에 명시적으로 지정
    private fun setApple(newApple: Apple) {
        apple = newApple
    }

    @Autowired
    @JvmName("setBananaFromKotlin") // 코틀린은 set형식의 메서드명이 없기에 명시적으로 지정
    private fun setBanana(banana: Banana) {
        this.banana = banana
    }
}
