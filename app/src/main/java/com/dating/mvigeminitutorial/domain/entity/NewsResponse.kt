package com.dating.mvigeminitutorial.domain.entity

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String? = null,
    val totalResults: Int? = null
)
