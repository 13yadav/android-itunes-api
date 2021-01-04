package com.strangecoder.itunes_api.repository

import com.strangecoder.itunes_api.db.ResultDatabase
import com.strangecoder.itunes_api.db.entities.RoomDbItem
import com.strangecoder.itunes_api.network.NetworkService

class Repository(
    private val database: ResultDatabase
) {

    fun getSearchResults(searchTerm: String) =
        NetworkService.retrofitService.getSearchResults(searchTerm)

    fun getLocalResults(searchTerm: String) =
        database.resultDao.getLocalResults(searchTerm)

    fun insertAllResults(resultItems: List<RoomDbItem>) =
        database.resultDao.insertAll(resultItems)

}