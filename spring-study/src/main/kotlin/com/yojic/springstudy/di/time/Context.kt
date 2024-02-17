package com.yojic.springstudy.di.time

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader

@Component
class Context(
    @Qualifier("sum")
    val strategy: IStrategy,
) {
    fun operation(path: String): Int {
        val resource = ClassPathResource(path)
        val reader = BufferedReader(InputStreamReader(resource.inputStream))
        var result = 0
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            result = strategy.strategy(result, line!!)
        }
        return result
    }
}
