package com.yojic.springstudy.di

import org.springframework.stereotype.Component
//@Component
class PorkPatty() : Patty {
    override fun price(): Int {
        return 1800
    }
}
