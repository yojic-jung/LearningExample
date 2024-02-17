package com.yojic.springstudy.di.time

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ContextTest(
    @Autowired
    val context: Context,
) {
    @Test
    fun `합 전략 테스트`() {
        val result = context.operation("numberFile.txt")
        println(result)
    }
}
