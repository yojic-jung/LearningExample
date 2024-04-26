//package com.yojic.querydslstudy.repository
//
//import com.querydsl.core.types.Projections
//import com.querydsl.jpa.impl.JPAQueryFactory
//import com.yojic.querydslstudy.dto.MemberDto
//import com.yojic.querydslstudy.entity.MemberEntity
//import com.yojic.querydslstudy.entity.QMemberEntity.memberEntity as member
//import com.yojic.querydslstudy.entity.QMemberRoleEntity.memberRoleEntity as memberRole
//import jakarta.persistence.EntityManager
//import org.springframework.stereotype.Repository
//
//@Repository
//class MemberQueryRepositoryImpl(
//    private val jpaQueryFactory: JPAQueryFactory,
//    private val em: EntityManager,
//) : MemberQueryRepository {
//    override fun findAllMembers(): List<MemberEntity>? {
//        return jpaQueryFactory
//            .select(
//                member,
//            ).from(member)
//            .fetch()
//    }
//
//    override fun findMembersByMemIdFetchJoin(memId: Long): MemberEntity? {
//        return jpaQueryFactory
//            .selectFrom(
//                member,
//            )
//            .where(member.memId.eq(memId))
//            .leftJoin(member.role, memberRole)
//            .fetchJoin()
//            .fetchOne()
//    }
//
//    override fun findMembersByMemIdNoFetchJoin(memId: Long): MemberEntity? {
//        return jpaQueryFactory
//            .select(member).from(member)
//            .where(member.memId.eq(memId))
//            .fetchOne()
//    }
//
//    override fun findAllMembersWithRoles(): List<MemberEntity>? {
//        return jpaQueryFactory
//            .select(
//                member,
//            ).from(member)
//            .leftJoin(memberRole)
//            .fetch()
//    }
//
//    override fun findAllMembersEmail(): List<MemberDto>? {
//        return jpaQueryFactory
//            .select(
//                Projections.fields(
//                    MemberDto::class.java,
//                    member.memId,
//                    member.email,
//                ),
//            ).from(member)
//            .fetch()
//    }
//
//    override fun findMemberEmailOnlyOne(): MemberEntity? {
//        return jpaQueryFactory
//            .select(
//                member,
//            ).from(member)
//            .fetchOne()
//    }
//
//    override fun findMemberEmailLimitOne(): MemberEntity? {
//        return jpaQueryFactory
//            .select(
//                member,
//            ).from(member)
//            .fetchFirst()
//    }
//}
