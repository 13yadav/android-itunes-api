package com.strangecoder.itunes_api.domain

import com.strangecoder.itunes_api.db.entities.RoomDbItem
import com.strangecoder.itunes_api.network.models.ResultItem

fun mapNetworkToDomain(resultItems: List<ResultItem>): List<SongItem> {
    return resultItems.map {
        SongItem(
            trackId = it.trackId,
            artistId = it.artistId,
            artistName = it.artistName,
            artistViewUrl = it.artistViewUrl,
            artworkUrl100 = it.artworkUrl100,
            artworkUrl600 = it.artworkUrl600,
            country = it.country,
            isStreamable = it.isStreamable,
            previewUrl = it.previewUrl,
            primaryGenreName = it.primaryGenreName,
            releaseDate = it.releaseDate,
            trackName = it.trackName,
            trackNumber = it.trackNumber,
            trackTimeMillis = it.trackTimeMillis,
            trackViewUrl = it.trackViewUrl,
        )
    }
}

fun mapNetworkToDatabase(resultItems: List<ResultItem>): List<RoomDbItem> {
    return resultItems.map {
        RoomDbItem(
            trackId = it.trackId,
            artistId = it.artistId,
            artistName = it.artistName,
            artistViewUrl = it.artistViewUrl,
            artworkUrl100 = it.artworkUrl100,
            artworkUrl600 = it.artworkUrl600,
            country = it.country,
            isStreamable = it.isStreamable,
            previewUrl = it.previewUrl,
            primaryGenreName = it.primaryGenreName,
            releaseDate = it.releaseDate,
            trackName = it.trackName,
            trackNumber = it.trackNumber,
            trackTimeMillis = it.trackTimeMillis,
            trackViewUrl = it.trackViewUrl,
        )
    }
}

fun mapDatabaseToDomain(roomDbItems: List<RoomDbItem>): List<SongItem> {
    return roomDbItems.map {
        SongItem(
            trackId = it.trackId,
            artistId = it.artistId,
            artistName = it.artistName,
            artistViewUrl = it.artistViewUrl,
            artworkUrl100 = it.artworkUrl100,
            artworkUrl600 = it.artworkUrl600,
            country = it.country,
            isStreamable = it.isStreamable,
            previewUrl = it.previewUrl,
            primaryGenreName = it.primaryGenreName,
            releaseDate = it.releaseDate,
            trackName = it.trackName,
            trackNumber = it.trackNumber,
            trackTimeMillis = it.trackTimeMillis,
            trackViewUrl = it.trackViewUrl,
        )
    }
}
