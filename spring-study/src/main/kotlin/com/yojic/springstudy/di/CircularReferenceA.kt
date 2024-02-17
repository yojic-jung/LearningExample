package com.yojic.springstudy.di

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

//@Component
class CircularReferenceA(
    @Lazy var circularReferenceB: com.yojic.springstudy.di.CircularReferenceB,
    @Autowired var noBeanImpl: com.yojic.springstudy.di.NoSuchBean,
) {
    fun `lazy init 테스트`() {
        println("lazy init 테스트")
    }
}
