package com.dating.mvigeminitutorial.data

import com.dating.mvigeminitutorial.domain.entity.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "9c2467fe5aba4c20963eb5f98ec50f02"

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse
}
