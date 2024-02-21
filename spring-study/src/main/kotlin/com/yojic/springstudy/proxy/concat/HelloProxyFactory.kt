package com.yojic.springstudy.proxy.concat

import org.springframework.beans.factory.FactoryBean
import java.lang.reflect.Proxy

class HelloProxyFactory(
    private val target: Any,
    private val serviceInterface: Class<*>?,
) : FactoryBean<Any> {
    override fun getObjectType(): Class<*>? = serviceInterface

    override fun getObject(): Any? = Proxy.newProxyInstance(
        javaClass.classLoader,
        arrayOf(serviceInterface),
        HelloConcatHandler(target),
    )
}
