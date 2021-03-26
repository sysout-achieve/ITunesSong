package com.gunt.itunessong.data.repository.network.response

data class ITunesResponse<T> (
    var resultCount:Int?,
    var results : List<T>?
)