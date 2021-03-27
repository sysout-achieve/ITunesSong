package com.gunt.itunessong.data.domain

data class Track(
    var trackId: Long,
    var artistName: String?,
    var collectionName: String?,
    var trackName: String?,
    var artworkUrl60: String?
)
