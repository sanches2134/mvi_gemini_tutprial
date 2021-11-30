package com.dating.mvigeminitutorial.domain.repository

import com.dating.mvigeminitutorial.domain.entity.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {

    suspend fun getNews(countryCode: String, pageNumber: Int): NewsResponse

}
