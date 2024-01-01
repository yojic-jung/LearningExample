package com.yojic.springstudy.toby.chap1.soc

interface CustomInfoExtract {
    fun `당첨 고객 정보 추출`(추첨번호: List<Any>): CustomInfo
}