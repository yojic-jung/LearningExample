package com.yojic.springstudy.testcode.example.dto

import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

class MemberDto(
    @field:Size(min=1)
    val name: String,
    @field:Positive
    val age: Int
)