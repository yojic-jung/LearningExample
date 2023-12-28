package com.yojic.querydslstudy.dto

import java.time.LocalDateTime

class MemberDto(
    var memId: Long = 0,
    var email: String = "",
    var passwd: String = "",
    var humanStatus: Int = 0,
    var failCnt: Int = 0,
    var lastFailTime: String? = null,
    var sysUpdateTime: LocalDateTime = LocalDateTime.now(),
    var sysCreateTime: LocalDateTime = LocalDateTime.now(),
)
