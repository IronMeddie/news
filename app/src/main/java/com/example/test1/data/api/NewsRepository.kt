package com.example.test1.data.api

import com.example.test1.data.db.ArctikDao
import com.example.test1.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService,private val articleDao: ArctikDao) {

    suspend fun getNews(countryCode:String, pageNumber: Int) = newsService.getHeadlines(countryCode,pageNumber)


    suspend fun getSearchNews(query: String, pageNumber: Int) = newsService.getEverything(query = query, page = pageNumber)


    fun getFavorites() = articleDao.getAllArt()

    suspend fun addToFavorites(article: Article) = articleDao.insert(article)

    suspend fun deliteFavorite(article: Article) = articleDao.delete(article)

}