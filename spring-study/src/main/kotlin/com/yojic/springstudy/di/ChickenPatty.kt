package com.yojic.springstudy.di

import org.springframework.stereotype.Component
@Component
class ChickenPatty() : com.yojic.springstudy.di.Patty {
    override fun price(): Int {
        return 1500
    }
}
