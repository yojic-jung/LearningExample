package com.yojic.springstudy.toby.chap1.di.way

import org.springframework.stereotype.Component

@Component
class ConstructorDi(
    var apple: Apple,
    var banana: Banana,
)
