package com.yojic.springstudy.beanfactory.processor.definition

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component

/**
 * Def: BeanDefinition을 BeanDefinitionRegistry에 저장해주는 후처리기
 * Desc: 커스텀 어노테이션이 붙은 클래스를 스프링 빈으로 등록해줌
 */
@Component
class CustomBeanDefinitionRegistryPostProcessor: BeanDefinitionRegistryPostProcessor {
    override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry) {
        // annotation filter 등록
        val componentScanner = ClassPathScanningCandidateComponentProvider(false)
        val annotationFilter = AnnotationTypeFilter(CustomBean::class.java)
        componentScanner.addIncludeFilter(annotationFilter)

        // basePackage에 대하여 빈 후보군 beanDefinition 추출
        val beanDefOfCandidates = componentScanner.findCandidateComponents("com.yojic.springstudy.beanfactory.processor")

        // 빈 후보들에 대하여 beanDefinition 생성 및 등록
        for (beanDef in beanDefOfCandidates) {
            val beanClass = Class.forName(beanDef.beanClassName)
            val beanName = beanClass.simpleName.replaceFirstChar { it.lowercase() }
            val beanDefBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass)

            // beanDefinition 속성 정의
            beanDefBuilder.setScope("singleton")
            beanDefBuilder.setPrimary(true)

            // 빈 등록
            registry.registerBeanDefinition(beanName, beanDefBuilder.beanDefinition)
        }
    }
}
