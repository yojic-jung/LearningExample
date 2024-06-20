package com.yojic.springstudy.transaction.proxy.compile

/**
 * Def. 컴파일 의존 프록시 예제를 위한 인터페이스
 * Desc. 예제를 위한 것으로 구현체는 존재하지 않음
 */
interface MemberRepository {
    fun create(member: Member): Long

    fun addRole(memberRole: MemberRole)

}
