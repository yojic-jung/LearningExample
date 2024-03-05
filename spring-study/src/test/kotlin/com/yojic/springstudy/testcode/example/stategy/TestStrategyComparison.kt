package com.yojic.springstudy.testcode.example.stategy

import com.yojic.springstudy.testcode.example.controller.MemberController
import com.yojic.springstudy.testcode.example.service.MemberService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.ApplicationContext

/**
 *  Def : 테스트 종류별 특징 비교
 *  Desc : 프로젝트 규모가 크면 아래 항목의 차이는 더 커질 수 있음
 *  +----------+---------------+-----------------+
 *  |          |  @WebMvcTest  | @SpringBootTest |
 *  +----------+---------------+-----------------+
 *  | 빈 갯수   |    122개      |    271개         |
 *  | 실행시간  |    45ms       |    468ms        |
 *  +----------+--------------+------------------+
 *  (24.03.04 기준)
 */
@WebMvcTest(MemberController::class)
class WebMvcTest(
    @Autowired
    private val iocContext: ApplicationContext,
    @Autowired
    private val memberController: MemberController,
    @MockBean
    private val memberService: MemberService,
) {
    @Test
    fun `@WebMvcTest시 컨텍스가 띄우는 빈 갯수 및 목록`() {
        // 컨텍스트가 띄운 전체 빈 목록
        val beanNames = iocContext.beanDefinitionNames
        
        // 내가 정의한 빈 목록
        val beanNameBySpring = iocContext.beanDefinitionNames.filter { beanName ->
            iocContext.getType(beanName)?.packageName?.startsWith("com.yojic") == true
        }
        
        // 전체 빈 갯수 및 이름
        println(beanNames.size)
        for (beanName in beanNames) println(beanName)

        // 내가 정의한 빈 갯수 및 이름
        println(beanNameBySpring.size)
        for (beanName in beanNameBySpring) println(beanName)
    }
}

@SpringBootTest
class SpringBootTest(
    @Autowired
    private val iocContext: ApplicationContext
) {
    @Test
    fun `@SpringBootTest시 컨텍스트가 띄우는 빈 갯수 및 목록`() {
        // 컨텍스트가 띄운 전체 빈 목록
        val beanNames = iocContext.beanDefinitionNames

        // 내가 정의한 빈 목록
        val beanNameBySpring = iocContext.beanDefinitionNames.filter { beanName ->
            iocContext.getType(beanName)?.packageName?.startsWith("com.yojic") == true
        }

        // 전체 빈 갯수 및 이름
        println(beanNames.size)
        for (beanName in beanNames) println(beanName)

        // 내가 정의한 빈 갯수 및 이름
        println(beanNameBySpring.size)
        for (beanName in beanNameBySpring) println(beanName)
    }
}