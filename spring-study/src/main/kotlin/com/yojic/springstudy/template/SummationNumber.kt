package com.yojic.springstudy.template

class SummationNumber(path: String) : AbstractTemplate(path) {
    override fun calculateResult(result: Int, line: String): Int {
        return result + line.toInt()
    }
}
