package com.example.test1.data.api

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getNews(countryCode:String, pageNumber: Int) = newsService.getHeadlines(countryCode,pageNumber)

}