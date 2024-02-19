package com.yojic.springstudy.template.callback

import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader

class Template(
    val callback: IStrategy,
) {
    fun operation(path: String): Int {
        val resource = ClassPathResource(path)
        val reader = BufferedReader(InputStreamReader(resource.inputStream))
        var result = 0
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            result = callback.strategy(result, line!!)
        }
        return result
    }
}
