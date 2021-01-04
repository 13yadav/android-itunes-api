package com.strangecoder.itunes_api.network

import com.strangecoder.itunes_api.network.models.SearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("search")
    fun getSearchResults(
            @Query("term") searchTerm: String
    ): Deferred<SearchResponse>
}