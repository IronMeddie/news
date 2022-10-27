package com.example.test1.models

data class NewsResp(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)