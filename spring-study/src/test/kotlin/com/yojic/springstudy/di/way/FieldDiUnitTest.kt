package com.yojic.springstudy.di.way

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class FieldDiUnitTest() {
    @Spy
    var banana: Banana = PhilipineBanana()

    @Mock
    lateinit var apple: Apple

    @InjectMocks
    lateinit var fieldDi: FieldDi

    @Test
    fun `테스트`() {
        fieldDi.diWorkingMethod()
    }
}
