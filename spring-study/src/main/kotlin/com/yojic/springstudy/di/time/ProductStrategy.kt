package com.yojic.springstudy.di.time

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Qualifier("product")
@Component
class ProductStrategy : IStrategy {
    override fun strategy(result: Int, line: String): Int {
        return if (result == 0) line.toInt() else result * line.toInt()
    }
}
