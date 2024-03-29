package com.yojic.springstudy.soc

class GiveawayMachineCompEx2(
    val customInfoExtract: CustomInfoExtract,
    val premiumMath: PremiumMath
) {
    fun `5자리 랜덤 숫자 추출`(): CustomInfo {
        val 추첨번호 = premiumMath.`랜덤 숫자 추출`(length = 5)
        return customInfoExtract.`당첨 고객 정보 추출`(추첨번호)
    }

    fun `5자리 랜덤 문자열 추출`(): CustomInfo {
        // 당첨 코드 추출
        val chars = ('A'..'Z')
        val 추첨코드 = (1..5)
            .map { chars.random() }
        return customInfoExtract.`당첨 고객 정보 추출`(추첨코드)
    }
}
