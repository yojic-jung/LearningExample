package com.yojic.springstudy.proxy.transaction.invocation

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class TransactionHandler(
    private val target: Any,
    private val transactionManager: PlatformTransactionManager,
    private val methodNamePatter: String,
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        return if (method!!.name.startsWith(methodNamePatter)) {
            invokeTransaction(method, args)
        } else {
            method.invoke(target, args)
        }
    }

    private fun invokeTransaction(method: Method?, args: Array<out Any>?): Any {
        val status = transactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            val returnVal = method?.invoke(target, args!!.get(0))
            transactionManager.commit(status)
            return returnVal ?: Any()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
            transactionManager.rollback(status)
            throw e.targetException
        } catch (e: RuntimeException) {
            e.printStackTrace()
            transactionManager.rollback(status)
            throw e
        }
    }
}
