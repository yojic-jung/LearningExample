package com.yojic.springstudy.toby.chap1.di

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

//@Component
class CircularReferenceA(
    @Lazy var circularReferenceB: CircularReferenceB,
    @Autowired var noBeanImpl: NoSuchBean,
) {
    fun `lazy init 테스트`() {
        println("lazy init 테스트")
    }
}
