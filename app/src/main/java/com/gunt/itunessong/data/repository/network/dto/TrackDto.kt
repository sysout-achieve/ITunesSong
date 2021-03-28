package com.gunt.itunessong.data.repository.network.dto

data class TrackDto(
    var trackId: Long,
    var artistName: String?,
    var trackName: String?,
    var collectionName: String?,
    var artworkUrl60: String?
) {
    var wrapperType: String = ""
    var artistId: Long = 0
    var kind: String = ""
    var collectionId: Long = 0
    var collectionCensoredName: String = ""
    var trackCensoredName: String = ""
    var artistViewUrl: String = ""
    var collectionViewUrl: String = ""
    var trackViewUrl: String = ""
    var previewUrl: String = ""
    var artworkUrl30: String = ""
    var artworkUrl100: String = ""
    var collectionPrice: Double = 0.0
    var vartrackPrice: Double = 0.0
    var releaseDate: String = ""
    var collectionExplicitness: String = ""
    var trackExplicitness: String = ""
    var discCount: Int = 0
    var discNumber: Int = 0
    var trackCount: Int = 0
    var trackNumber: Int = 0
    var trackTimeMillis: Long = 0
    var country: String = ""
    var currency: String = ""
    var primaryGenreName: String = ""
    var isStreamable: Boolean = false
}
