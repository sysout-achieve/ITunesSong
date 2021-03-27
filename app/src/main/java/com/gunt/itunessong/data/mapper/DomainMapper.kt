package com.gunt.itunessong.data.mapper

interface DomainMapper<Dto, DomainModel> {

    fun mapToDomainModel(model: Dto): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): Dto
}
