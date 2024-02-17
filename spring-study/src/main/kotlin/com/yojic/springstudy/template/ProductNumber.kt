package com.yojic.springstudy.template

class ProductNumber(path: String) : AbstractTemplate(path) {
    override fun calculateResult(result: Int, line: String): Int {
        return if (result == 0) line.toInt() else result * line.toInt()
    }
}
