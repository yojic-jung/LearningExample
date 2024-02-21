package com.yojic.springstudy.proxy.transaction.factory

import com.yojic.springstudy.proxy.transaction.invocation.TransactionHandler
import org.springframework.beans.factory.FactoryBean
import org.springframework.transaction.PlatformTransactionManager
import java.lang.reflect.Proxy

class TxProxyFactoryBean(
    private val target: Any,
    private val transactionManager: PlatformTransactionManager,
    private val methodNamePatter: String,
    private val serviceInterface: Class<*>?,
) : FactoryBean<Any> {
    override fun getObjectType(): Class<*>? = serviceInterface

    override fun getObject(): Any? =
        Proxy.newProxyInstance(
            javaClass.classLoader,
            arrayOf(serviceInterface),
            TransactionHandler(target, transactionManager, methodNamePatter),
        )

    fun isSinglton(): Boolean = false
}
