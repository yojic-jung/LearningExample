package com.yojic.springstudy.observer

import org.springframework.context.event.EventListener
import org.springframework.core.annotation.Order
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class AppleEventListener {
    @Order(2)
    @Async
    @EventListener
    fun handleAppleEvent(apple: AppleEvent) {
        println("사과의 한 종류인 ${apple.name} 이벤트 발행")
    }
}
