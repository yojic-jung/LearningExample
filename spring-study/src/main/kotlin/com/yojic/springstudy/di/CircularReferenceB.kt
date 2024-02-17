package com.yojic.springstudy.di

import org.springframework.stereotype.Component

//@Component
class CircularReferenceB(
    var circularReferenceA: com.yojic.springstudy.di.CircularReferenceA,
)
