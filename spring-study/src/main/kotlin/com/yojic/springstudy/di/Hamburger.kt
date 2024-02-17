package com.yojic.springstudy.di

import org.springframework.stereotype.Component

@Component
class Hamburger(val patty: Patty) {
    fun hamburgerPrice(): Int {
        return patty.price() + 1000
    }
}
