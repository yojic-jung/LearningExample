package com.yojic.springstudy.di.way

import org.springframework.stereotype.Component

@Component
class ConstructorDi(
    var apple: Apple,
    var banana: Banana,
)
