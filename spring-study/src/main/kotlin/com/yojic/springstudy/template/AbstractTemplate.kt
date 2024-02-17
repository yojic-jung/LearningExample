package com.yojic.springstudy.template

import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader

abstract class AbstractTemplate(
    private val path: String
) {
    fun templateMethod(): Int {
        val resource = ClassPathResource(path)
        val reader = BufferedReader(InputStreamReader(resource.inputStream))
        var result = 0
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            result = calculateResult(result, line!!)
        }
        return result
    }

    protected abstract fun calculateResult(result: Int, line: String): Int
}
