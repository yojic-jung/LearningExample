package com.yojic.springstudy.proxy.concat

import org.springframework.stereotype.Component
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class HelloConcatHandler(
    private val target: Any,
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        val obj = method?.invoke(target, args?.get(0))
        return if (obj is String) "$obj, Hello" else obj
    }
}
