package com.gunt.itunessong.data.mapper

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.db.entity.TrackEntity

class TrackEntityMapper : DomainMapper<TrackEntity, Track> {

    override fun mapToDomainModel(model: TrackEntity): Track {
        return Track(
            trackId = model.trackId,
            artistName = model.artistName,
            collectionName = model.collectionName,
            trackName = model.trackName,
            artworkUrl60 = model.artworkUrl60
        )
    }

    fun toDomainModelList(initial: List<TrackEntity>): List<Track> =
        initial.map { mapToDomainModel(it) }

    override fun mapFromDomainModel(domainModel: Track): TrackEntity {
        return TrackEntity(
            trackId = domainModel.trackId,
            artistName = domainModel.artistName,
            collectionName = domainModel.collectionName,
            trackName = domainModel.trackName,
            artworkUrl60 = domainModel.artworkUrl60
        )
    }
}
