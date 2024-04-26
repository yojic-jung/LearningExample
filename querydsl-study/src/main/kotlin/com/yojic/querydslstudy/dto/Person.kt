package com.yojic.querydslstudy.dto

data class Person(
    val id: Int,
    val birth: Int,
    val phone: Int,
) {
    init {
        require(id > 0)
        require(birth.toString().length == 6)
        require(phone.toString().length in 11..12)
    }
}
