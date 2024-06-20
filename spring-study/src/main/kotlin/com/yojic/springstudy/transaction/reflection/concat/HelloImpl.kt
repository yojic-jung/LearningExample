package com.yojic.springstudy.transaction.reflection.concat

class HelloImpl : Hello {
    override fun hello(name: String): String = name

    override fun hi(name: String): String = name
}
