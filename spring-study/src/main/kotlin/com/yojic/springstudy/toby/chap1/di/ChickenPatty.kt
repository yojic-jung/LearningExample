package com.yojic.springstudy.toby.chap1.di

import org.springframework.stereotype.Component
@Component
class ChickenPatty() : Patty {
    override fun price(): Int {
        return 1500
    }
}
