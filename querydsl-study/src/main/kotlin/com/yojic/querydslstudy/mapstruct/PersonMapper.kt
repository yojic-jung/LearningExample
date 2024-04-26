package kr.co.hmcnetworks.backoffice.adapter.data.jpa.repository.carenation.common

import com.yojic.querydslstudy.entity.PersonEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface PersonMapper {
    companion object {
        val INSTANCE: PersonMapper = Mappers.getMapper(PersonMapper::class.java)
    }

    @JvmOverloads
    fun convertToEntity(personDto: PersonDto): PersonEntity
}
