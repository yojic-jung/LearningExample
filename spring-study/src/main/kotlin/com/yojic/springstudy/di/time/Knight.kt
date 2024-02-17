package com.yojic.springstudy.di.time

class Knight(
        val sword: Sword
) {
    fun skill(){
        sword.attack()
    }
}