package com.yojic.springstudy.toby.chap1.di.way

import org.springframework.stereotype.Component

@Component
class ThaiBanana : Banana {
    override fun bananaMethod(): String = "태국 banana입니다."
}
