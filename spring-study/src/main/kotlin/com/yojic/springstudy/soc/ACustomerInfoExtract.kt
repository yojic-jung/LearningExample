package com.yojic.springstudy.soc

class ACustomerInfoExtract() : CustomInfoExtract {
    override fun `당첨 고객 정보 추출`(추첨번호: List<Any>): CustomInfo {
        // api 요청으로 고객 정보 추출 로직
        val customRequest = HttpCustomRequest("https://www.a-corp.com")
        customRequest.setParameter("추첨번호", 추첨번호.toString())
        val customInfo = customRequest.get()
        customRequest.close()
        return customInfo
    }
}
