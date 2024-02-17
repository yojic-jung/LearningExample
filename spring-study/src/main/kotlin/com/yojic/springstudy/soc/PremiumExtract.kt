package com.yojic.springstudy.soc

class PremiumExtract : PremiumMath {

    override fun `랜덤 숫자 추출`(length: Int): List<Int> {
        return (0..9).map { (1..45).random() }
    }
}