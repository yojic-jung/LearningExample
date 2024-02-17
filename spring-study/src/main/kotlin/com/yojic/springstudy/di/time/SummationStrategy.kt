package com.yojic.springstudy.di.time

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Qualifier("sum")
@Component
class SummationStrategy : IStrategy {
    override fun strategy(result: Int, line: String): Int {
        return result + line.toInt()
    }
}
