package com.yojic.springstudy.toby.chap1.di.way

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class PhilipineBanana : Banana {
    override fun bananaMethod(): String = "필리핀 banana입니다."
}
