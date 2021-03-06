package com.strangecoder.itunes_api.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resultItems")
data class RoomDbItem(
    @PrimaryKey(autoGenerate = false)
    val trackId: Int?,
    val artistId: Int?,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl600: String?,
    val country: String?,
    val isStreamable: Boolean?,
    val previewUrl: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackTimeMillis: Int?,
    val trackViewUrl: String?,
)