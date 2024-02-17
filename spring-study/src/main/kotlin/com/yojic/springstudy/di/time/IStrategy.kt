package com.yojic.springstudy.di.time

interface IStrategy {
    fun strategy(result: Int, line: String): Int
}
