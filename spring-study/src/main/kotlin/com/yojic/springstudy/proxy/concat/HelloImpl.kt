package com.yojic.springstudy.proxy.concat

class HelloImpl : Hello {
    override fun hello(name: String): String = name

    override fun hi(name: String): String = name
}
