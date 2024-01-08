package com.yojic.springstudy.toby.chap1.di

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
// @Qualifier("special")
@Component
class BeefPatty() : Patty {
    override fun price(): Int {
        return 2000
    }
}
