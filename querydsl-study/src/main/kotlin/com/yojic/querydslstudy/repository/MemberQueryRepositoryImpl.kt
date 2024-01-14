package com.yojic.querydslstudy.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yojic.querydslstudy.dto.MemberDto
import com.yojic.querydslstudy.entity.Member
import com.yojic.querydslstudy.entity.QMember
import com.yojic.querydslstudy.entity.QMemberRole
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class MemberQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val em: EntityManager,
) : MemberQueryRepository {
    override fun findAllMembers(): List<Member>? {
        val member = QMember.member
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .fetch()
    }

    override fun findMembersByMemIdFetchJoin(memId: Long): Member? {
        val member = QMember.member
        val role = QMemberRole.memberRole
        return jpaQueryFactory
            .selectFrom(
                member,
            )
            .where(member.memId.eq(memId))
            .leftJoin(member.role, role)
            .fetchJoin()
            .fetchOne()
    }

    override fun findMembersByMemIdNoFetchJoin(memId: Long): Member? {
        val member = QMember.member
        val role = QMemberRole.memberRole
        return jpaQueryFactory
            .select(member).from(member)
            .where(member.memId.eq(memId))
            .fetchOne()
    }

    override fun findAllMembersWithRoles(): List<Member>? {
        val member = QMember.member
        val role = QMemberRole.memberRole
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .leftJoin(role)
            .fetch()
    }

    override fun findAllMembersEmail(): List<MemberDto>? {
        val member = QMember.member
        return jpaQueryFactory
            .select(
                Projections.fields(
                    MemberDto::class.java,
                    member.memId,
                    member.email,
                ),
            ).from(member)
            .fetch()
    }

    override fun findMemberEmailOnlyOne(): Member? {
        val member = QMember.member
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .fetchOne()
    }

    override fun findMemberEmailLimitOne(): Member? {
        val member = QMember.member
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .fetchFirst()
    }
}
