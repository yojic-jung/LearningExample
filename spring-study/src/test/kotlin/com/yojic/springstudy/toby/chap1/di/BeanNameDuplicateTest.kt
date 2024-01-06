package com.yojic.springstudy.toby.chap1.di

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootTest
class BeanNameDuplicateTest {

    @Test
    fun `빈이름 중복 - 오버라이딩 테스트`() {
        val ctx = AnnotationConfigApplicationContext(AppConfig::class.java)

        var beanNameDuplicateCnt = 0
        // 등록 빈 출력
        for (beanName in ctx.beanDefinitionNames) {
            if (beanName == "beanNameDuplicate") beanNameDuplicateCnt++
        }
        /* 상속의 오버라이딩 처럼 메서드나 속성이 덮어쓰워지는게 아님
         * 빈 등록도 하나밖에 안됨
         * 파일 덮어쓰기 처럼 이전에 등록한 빈 무시하고 새로운 빈으로 덮어써진다는 뜻임
         * 자동빈이 우선적으로 빈으로 등록되고 수동빈 내용이 그 이후에 등록됨
         * 이때 동일한 것이 있다면 수동빈으로 덮어쓰기 되어 등록된다는 것
         */
        assertThat(beanNameDuplicateCnt).isEqualTo(1)

        ctx.close()
    }
}
