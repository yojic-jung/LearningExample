package com.yojic.springstudy.soc

abstract class AbstractGiveawayMachine {
    fun `5자리 랜덤 숫자 추출`(): List<Int> {
        val 추첨번호 = (0..9).map { (0..9).random() }
        return 추첨번호
    }

    fun `5자리 랜덤 문자열 추출`(): List<Char> {
        // 당첨 코드 추출
        val chars = ('A'..'Z')
        val 추첨코드 = (1..5)
            .map { chars.random() }
        return 추첨코드
    }

    abstract fun `당첨 고객 정보 추출`(추첨번호: List<Any>): CustomInfo
}
