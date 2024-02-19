package com.yojic.springstudy.template.callback

interface IStrategy {
    fun strategy(result: Int, line: String): Int
}