package com.yojic.springstudy.toby.chap1.di

import org.springframework.stereotype.Component
//@Component
class PorkPatty() : Patty {
    override fun price(): Int {
        return 1800
    }
}
