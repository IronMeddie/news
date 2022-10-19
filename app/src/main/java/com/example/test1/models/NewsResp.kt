package com.example.test1.models

data class NewsResp(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)