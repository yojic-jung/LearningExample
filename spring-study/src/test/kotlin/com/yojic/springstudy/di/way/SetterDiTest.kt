package com.yojic.springstudy.di.way

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SetterDiTest(
    @Autowired
    var setterDi: SetterDi,
) {
    @Test
    fun `세터 주입 의존주입 테스트`() {
        Assertions.assertThat(setterDi.apple).isNotNull()
        Assertions.assertThat(setterDi.banana).isNotNull()
    }
}
