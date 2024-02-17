package com.yojic.springstudy.soc

import com.yojic.springstudy.di.Patty


class BeefPatty() : Patty {
    override fun price(): Int {
        return 4000
    }
}
