package com.yojic.springstudy.toby.chap1.soc

class GiveawayMachineComp(
    val customInfoExtract: CustomInfoExtract,
) {
    fun `5자리 랜덤 숫자로 고객 정보 추출`(): CustomInfo {
        val 추첨번호 = (0..9).map { (0..9).random() }
        return customInfoExtract.`당첨 고객 정보 추출`(추첨번호)
    }

    fun `5자리 랜덤 문자열로 고객 정보 추출`(): CustomInfo {
        // 당첨 코드 추출
        val chars = ('A'..'Z')
        val 추첨코드 = (1..5)
            .map { chars.random() }
        return customInfoExtract.`당첨 고객 정보 추출`(추첨코드)
    }
}
