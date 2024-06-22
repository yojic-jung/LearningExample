package com.yojic.springstudy.transaction.proxy.compile

import com.yojic.springstudy.transaction.proxy.sample.MemberDto

/**
 * 프록시와 타깃의 상위 인터페이스
 */
interface MemberService {
    fun create(memberDto: MemberDto)
}
