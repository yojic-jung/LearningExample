package com.yojic.springstudy.di

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GiveawayMachineCompDITest(
    @Autowired
    val giveawayMachineCompDI: com.yojic.springstudy.di.GiveawayMachineCompDI,
) {
    @Test
    fun test() {
        val 고객정보 = giveawayMachineCompDI.`5자리 랜덤 문자열로 고객 정보 추출`()
        print(고객정보)
    }
}
