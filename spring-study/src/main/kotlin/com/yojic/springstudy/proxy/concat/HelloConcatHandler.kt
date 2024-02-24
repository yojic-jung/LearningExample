package com.yojic.springstudy.proxy.concat

import org.springframework.stereotype.Component
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class HelloConcatHandler(
    private val target: Any,
    private val methodNamePatter: String,
) : InvocationHandler {

    /**
     * 타깃과 메서드 선정 알고리즘을 모두 가지고 있음
     * 따라서 스프링 빈으로 등록 불가
     * 그래서 팩토리 빈에서 직접 생성하도록 함
     *
     * 타깃마다 메서드 패턴이 다르므로 공유하기 어려움
     */

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        val obj = if (method!!.name.startsWith(methodNamePatter)) {
            method?.invoke(target, args?.get(0))
        } else {
            method.invoke(target, args!!.get(0))
        }
        return if (obj is String) "$obj, Hello" else obj
    }
}
