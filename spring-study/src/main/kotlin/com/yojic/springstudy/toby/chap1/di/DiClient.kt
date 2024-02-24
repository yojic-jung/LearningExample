package com.yojic.springstudy.toby.chap1.di

import org.springframework.stereotype.Component

@Component
class DiClient(
        val runtimeDi: RuntimeDi,
) {
}