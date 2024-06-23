package com.yojic.springstudy.transaction.proxy.dynamic

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * 타깃(원본객체)과 메서드 패턴을 상태로 가지고 있음
 * 객체마다 사용하는 메서드 패턴은 다 다를 수 있음
 * 즉, 객체와 메서드 패턴을 모두 상태로 가지고 있다는 것은 특정 객체에 종속 된다는 것
 * 따라서 공유자원으로 사용하기에 좋지 않음(스프링 빈 등록 불가)
 * 프록시 팩토리 빈에서 직접 생성하여 사용
 * 하나의 InvocationHandler(부가기능) 마다 하나의 프록시 팩토리 빈이 필요함
 */
class TransactionHandler(
    private val target: Any,
    private val transactionManager: PlatformTransactionManager,
    private val methodNamePatter: String,
) : InvocationHandler {
    override fun invoke(
        proxy: Any?,
        method: Method?,
        args: Array<out Any>?,
    ): Any? {
        return if (method!!.name.startsWith(methodNamePatter)) {
            invokeTransaction(method, args)
        } else {
            method.invoke(target, args!!.get(0))
        }
    }

    private fun invokeTransaction(
        method: Method?,
        args: Array<out Any>?,
    ): Any? {
        val status = transactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            val returnVal = method?.invoke(target, args!!.get(0))
            transactionManager.commit(status)
            return returnVal
        } catch (e: InvocationTargetException) {
            transactionManager.rollback(status)
            throw e.targetException
        } catch (e: RuntimeException) {
            transactionManager.rollback(status)
            throw e
        }
    }
}
