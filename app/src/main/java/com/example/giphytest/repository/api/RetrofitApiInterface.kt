package com.example.giphytest.repository.api

import com.example.giphytest.model.GiphyResponse
import com.example.giphytest.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiInterface {

    @GET(Constants.SEARCH_URL)
    suspend fun getSearchedGiphy(@Query("q")  str : String): GiphyResponse

    @GET(Constants.TRENDING_URL)
    suspend fun getTrendingGiphy(): GiphyResponse
}