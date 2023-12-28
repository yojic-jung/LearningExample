package com.yojic.querydslstudy.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yojic.querydslstudy.dto.MemberDto
import com.yojic.querydslstudy.entity.MemberEntity
import com.yojic.querydslstudy.entity.QMemberEntity
import com.yojic.querydslstudy.entity.QMemberRoleEntity
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class MemberQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val em: EntityManager,
) : MemberQueryRepository {
    override fun findAllMembers(): List<MemberEntity>? {
        val member = QMemberEntity.memberEntity
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .fetch()
    }

    override fun findAllMembersFetchJoin(): List<MemberEntity>? {
        val member = QMemberEntity.memberEntity
        val role = QMemberRoleEntity.memberRoleEntity
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .leftJoin(role)
            .where(member.memId.eq(role.memId))
            .fetchJoin()
            .fetch()
    }

    override fun findAllMembersWithRoles(): List<MemberEntity>? {
        val member = QMemberEntity.memberEntity
        val role = QMemberRoleEntity.memberRoleEntity
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .leftJoin(role)
            .where(member.memId.eq(role.memId))
            .fetch()
    }
    override fun findAllMembersEmail(): List<MemberDto>? {
        val member = QMemberEntity.memberEntity
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

    override fun findMemberEmailOnlyOne(): MemberEntity? {
        val member = QMemberEntity.memberEntity
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .fetchOne()
    }
    override fun findMemberEmailLimitOne(): MemberEntity? {
        val member = QMemberEntity.memberEntity
        return jpaQueryFactory
            .select(
                member,
            ).from(member)
            .fetchFirst()
    }


}
