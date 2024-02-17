package com.yojic.springstudy.resources

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.Resource
import org.springframework.core.io.support.ResourcePatternResolver

@SpringBootTest
class ResourcesTest {

    @Autowired
    lateinit var resourcePatternResolver: ResourcePatternResolver

    @Test
    fun `리소스 파일 추출`() {
        val pattern = "classpath*:com/yojic/springstudy/toby/chap1/observer/**"
        val resources: Array<Resource> = resourcePatternResolver.getResources(pattern)

        for (resource in resources) {
            println(resource.filename)
        }
    }
}
