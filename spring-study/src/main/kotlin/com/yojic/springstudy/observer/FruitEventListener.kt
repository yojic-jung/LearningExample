package com.yojic.springstudy.observer

import org.springframework.context.event.EventListener
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
class FruitEventListener {
    @Order(1)
    @EventListener
    fun handleAppleEvent(apple: AppleEvent) {
        println("과일의 한 종류인 ${apple.name} 이벤트 발행")
    }
}