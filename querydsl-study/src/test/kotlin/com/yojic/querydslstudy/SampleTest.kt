package com.yojic.querydslstudy

import kr.co.hmcnetworks.backoffice.adapter.data.jpa.repository.carenation.common.PersonDto
import kr.co.hmcnetworks.backoffice.adapter.data.jpa.repository.carenation.common.PersonMapper
import kr.co.hmcnetworks.backoffice.adapter.data.jpa.repository.carenation.common.PersonRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SampleTest {
//    @Test
//    fun `mapper test`() {
//        val personRequest = PersonRequest(name = "brown", age = 20)
//        val personDto = PersonMapper.INSTANCE.convertToDto(personRequest)
//        println(personDto)
//        assertThat(personDto.name).isEqualTo(personRequest.name)
//        assertThat(personDto.age).isEqualTo(personRequest.age)
//    }

    @Test
    fun `entity mapper test`() {
        val personDto = PersonDto(name = "brown", age = 20)
        val personEntity = PersonMapper.INSTANCE.convertToEntity(personDto)
        println(personEntity.id)
        println(personEntity.name)
        println(personEntity.age)
        assertThat(personEntity.name).isEqualTo(personDto.name)
        assertThat(personEntity.age).isEqualTo(personDto.age)
    }
}
