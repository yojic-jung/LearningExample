package com.yojic.springstudy.soc

class HttpCustomRequest(val requestDomain: String) {

    fun setParameter(name: String, value: String) {
    }

    fun close() {
    }

    fun get(): CustomInfo {
        return CustomInfo(name = "tony", birth = "930123", address = "서울시 관악구")
    }
}
