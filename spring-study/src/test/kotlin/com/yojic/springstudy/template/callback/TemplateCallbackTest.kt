package com.yojic.springstudy.template.callback

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TemplateCallbackTest {

    @Test
    fun `템플릿 콜백 summation 테스트`() {
        val templateClass = Template(object : IStrategy {
            override fun strategy(result: Int, line: String): Int {
                return result + line.toInt()
            }
        })

        val result = templateClass.operation("numberFile.txt")

        assertThat(result).isEqualTo(15)
    }

    @Test
    fun `템플릿 콜백 product 테스트`() {
        val templateClass = Template(object : IStrategy {
            override fun strategy(result: Int, line: String): Int {
                return if (result == 0) line.toInt() else result * line.toInt()
            }
        })

        val result = templateClass.operation("numberFile.txt")

        assertThat(result).isEqualTo(120)
    }
}
