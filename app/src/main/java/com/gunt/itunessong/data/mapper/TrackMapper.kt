package com.gunt.itunessong.data.mapper

import com.gunt.itunessong.data.repository.network.dto.Song
import com.gunt.itunessong.data.domain.Track

class TrackMapper : DomainMapper<Song, Track> {
    override fun mapToDomainModel(model: Song): Track =
        Track(
            trackId = model.trackId,
            artistName = model.artistName,
            collectionName = model.collectionName,
            trackName = model.trackName,
            artworkUrl60 = model.artworkUrl60
        )

    fun toDomainModelList(initial: List<Song>): List<Track> =
        initial.map { mapToDomainModel(it) }

    override fun mapFromDomainModel(domainModel: Track): Song {
       return Song(
            trackId = domainModel.trackId,
            artistName = domainModel.artistName,
            trackName = domainModel.trackName,
            collectionName = domainModel.collectionName,
            artworkUrl60 = domainModel.artworkUrl60
        )
    }
}
