package com.gunt.itunessong.data.mapper

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.network.dto.TrackDto

class TrackDtoMapper : DomainMapper<TrackDto, Track> {
    override fun mapToDomainModel(model: TrackDto): Track =
        Track(
            trackId = model.trackId,
            artistName = model.artistName,
            collectionName = model.collectionName,
            trackName = model.trackName,
            artworkUrl60 = model.artworkUrl60
        )

    fun toDomainModelList(initial: List<TrackDto>): List<Track> =
        initial.map { mapToDomainModel(it) }

    override fun mapFromDomainModel(domainModel: Track): TrackDto {
        return TrackDto(
            trackId = domainModel.trackId,
            artistName = domainModel.artistName,
            trackName = domainModel.trackName,
            collectionName = domainModel.collectionName,
            artworkUrl60 = domainModel.artworkUrl60
        )
    }
}
