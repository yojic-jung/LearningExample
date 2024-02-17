package com.yojic.springstudy.soc

interface CustomInfoExtract {
    fun `당첨 고객 정보 추출`(추첨번호: List<Any>): CustomInfo
}