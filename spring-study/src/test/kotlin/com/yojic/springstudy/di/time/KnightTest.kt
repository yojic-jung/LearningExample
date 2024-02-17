package com.yojic.springstudy.di.time

import org.junit.jupiter.api.Test

class KnightTest {
    @Test
    fun 테스트() {
        val knight = Knight(Excalibur())
        knight.skill()
    }
}
