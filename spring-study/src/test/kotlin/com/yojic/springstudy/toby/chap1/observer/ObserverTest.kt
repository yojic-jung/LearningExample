package com.yojic.springstudy.toby.chap1.observer

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher

@SpringBootTest
class ObserverTest(
    @Autowired
    val eventPublisher: ApplicationEventPublisher
) {

    @Test
    fun `이벤트 발행-구독 옵저버 패턴 테스트`() {
        eventPublisher.publishEvent(AppleEvent("청송사과"))
    }
}
