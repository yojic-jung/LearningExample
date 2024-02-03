package com.yojic.springstudy.toby.chap1.di

import org.springframework.stereotype.Component

@Component
class Hamburger(val patty: Patty) {
    fun hamburgerPrice(): Int {
        return patty.price() + 1000
    }
}
