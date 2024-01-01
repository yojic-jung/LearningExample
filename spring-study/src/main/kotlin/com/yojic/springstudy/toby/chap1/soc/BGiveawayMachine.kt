package com.yojic.springstudy.toby.chap1.soc

class BGiveawayMachine : AbstractGiveawayMachine() {
    override fun `당첨 고객 정보 추출`(추첨번호: List<Any>): CustomInfo {
        // api 요청으로 고객 정보 추출 로직
        val customRequest = HttpCustomRequest("https://www.b-corp.com")
        customRequest.setParameter("추첨번호", 추첨번호.toString())
        val customInfo = customRequest.get()
        customRequest.close()
        return customInfo
    }
}
