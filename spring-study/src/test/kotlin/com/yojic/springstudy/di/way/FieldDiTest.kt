package com.yojic.springstudy.di.way

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FieldDiTest(
    @Autowired
    var fieldDi: FieldDi,
) {
    @Test
    fun `필드 주입 의존주입 테스트`() {
        Assertions.assertThat(fieldDi.apple).isNotNull()
        Assertions.assertThat(fieldDi.banana).isNotNull()
    }
}
