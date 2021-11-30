package com.dating.mvigeminitutorial.domain.repository

import com.dating.mvigeminitutorial.data.NewsApi

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsApi.getNews(countryCode, pageNumber)
}
