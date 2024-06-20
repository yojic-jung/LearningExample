package com.yojic.springstudy.transaction.proxy.dynamic

import org.springframework.beans.factory.FactoryBean
import org.springframework.transaction.PlatformTransactionManager
import java.lang.reflect.Proxy

/**
 * Def. 프록시 팩토리 빈
 * Desc.
 * - 타깃 인터페이스를 파라미터로 제공해주어 타깃과 같은 인터페이스 타입을 구현해야하는 번거로움을 없애줌
 * - 하나의 클래스 내의 메서드에서 중복되는 코드를 제거할 수 있음
 * - 허나 xml 설정 중복 문제가 발생
 */
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
}
