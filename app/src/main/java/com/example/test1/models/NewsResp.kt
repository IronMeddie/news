package com.example.test1.models

data class NewsResp(
    var articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)