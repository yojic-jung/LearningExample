package com.yojic.springstudy.beanfactory.processor.proxy

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

/**
 * 부가기능을 적용한 프록시 객체가 실제 타깃 객체를 대신하여 싱글톤 레지스트리에 저장되는지 확인하기 위한
 * 테스트 샘플 클래스
 */
@Aspect
@Component
class TestAspect {
    @Around("execution(* com.yojic.springstudy.beanfactory.processor.proxy.SampleTarget.*(..))")
    fun proxyMethod(joinPoint: ProceedingJoinPoint): Any? {
        println("beforeMethod")
        val result = joinPoint.proceed() // 타겟 메서드 실행
        println("afterMethod")
        return result
    }
}

@Component
class SampleTarget {
    fun targetMethod() {
        println("targetMethod")
    }
}
