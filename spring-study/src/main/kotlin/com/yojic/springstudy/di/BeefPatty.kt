package com.yojic.springstudy.di

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
// @Qualifier("special")
//@Component
class BeefPatty() : com.yojic.springstudy.di.Patty {
    override fun price(): Int {
        return 2000
    }
}
