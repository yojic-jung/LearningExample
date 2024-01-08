package com.yojic.springstudy.toby.chap1.di

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
// @Primary
@Component
class PorkPatty() : Patty {
    override fun price(): Int {
        return 1800
    }
}
