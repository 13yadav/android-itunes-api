package com.strangecoder.itunes_api.network.models

data class SearchResponse(
    val resultCount: Int,
    val results: List<ResultItem>
)