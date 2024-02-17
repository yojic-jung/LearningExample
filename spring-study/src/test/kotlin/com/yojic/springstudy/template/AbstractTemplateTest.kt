package com.yojic.springstudy.template

import org.junit.jupiter.api.Test

class AbstractTemplateTest {
    @Test
    fun summationTest() {
        val templateClass = SummationNumber("numberFile.txt")
        val result = templateClass.templateMethod()
        println(result)
    }

    @Test
    fun productTest() {
        val templateClass = ProductNumber("numberFile.txt")
        val result = templateClass.templateMethod()
        println(result)
    }
}