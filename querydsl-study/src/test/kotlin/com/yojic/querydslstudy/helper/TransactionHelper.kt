package com.yojic.querydslstudy.helper

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TransactionHelper {

    @Transactional(readOnly = true)
    fun <T> getResult(task: TransactionalTask<T>): T {
        return task.execute()
    }
}

interface TransactionalTask<T> {
    fun execute(): T
}
