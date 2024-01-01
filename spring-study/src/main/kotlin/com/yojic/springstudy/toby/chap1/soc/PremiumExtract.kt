package com.yojic.springstudy.toby.chap1.soc

class PremiumExtract : PremiumMath {

    override fun `랜덤 숫자 추출`(length: Int): List<Int> {
        return (0..9).map { (1..45).random() }
    }
}