package com.dating.mvigeminitutorial.domain.entity

data class Article(
        var id: Int? = null,
        val author: String?=null,
        val content: String?=null,
        val description: String?=null,
        val publishedAt: String?=null,
        val source: Source?=null,
        val title: String?=null,
        val url: String?=null,
        val urlToImage: String?=null
)
