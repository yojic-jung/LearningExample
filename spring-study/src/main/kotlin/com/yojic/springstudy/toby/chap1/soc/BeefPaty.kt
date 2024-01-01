package com.yojic.springstudy.toby.chap1.soc

import com.yojic.springstudy.toby.chap1.di.Patty

class BeefPatty() : Patty {
    override fun price(): Int {
        return 4000
    }
}
