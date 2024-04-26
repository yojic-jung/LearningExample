package com.yojic.springstudy.beanfactory.processor

import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target

/**
 * Def: 스프링 빈으로 등록해주는 커스텀 어노테이션
 * Desc: 커스텀 어노테이션을 통해 스프링 의존도가 없는 모듈에서도 스프링 빈처럼 사용할 수 있음
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
annotation class CustomBean
