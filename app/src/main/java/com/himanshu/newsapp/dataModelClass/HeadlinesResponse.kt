package com.himanshu.newsapp.dataModelClass

import kotlinx.serialization.Serializable

@Serializable
data class HeadlinesResponseResult(
    val status: String,
    val totalResults: Long,
    val articles: List<Article>
)

@Serializable
data class Article(
    val source: Article,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

@Serializable
data class Source (
    val id: String? = null,
    val name: String
)

