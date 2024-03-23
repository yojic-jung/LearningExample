package com.yojic.querydslstudy.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.boot.jackson.JsonComponent
import org.springframework.validation.annotation.Validated

class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        protected set
    var name: String? = null
        protected set
    var age: Int? = null
        protected set

    constructor(id: Int, name: String, age: Int) {
        this.id = id
        this.name = name
        this.age = age
    }

    @JvmOverloads
    constructor(name: String, id: Int, age: Int) {
        this.id = id
        this.name = name
        this.age = age
    }

}
