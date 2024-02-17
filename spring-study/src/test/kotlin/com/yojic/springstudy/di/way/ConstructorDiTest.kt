package com.yojic.springstudy.di.way

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConstructorDiTest(
    @Autowired
    var constructorDi: ConstructorDi,
) {
    @Test
    fun `생성자 주입 의존주입 테스트`() {
        assertThat(constructorDi.apple).isNotNull()
        assertThat(constructorDi.banana).isNotNull()
    }
}
