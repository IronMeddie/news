package com.example.test1.data.api

import com.example.test1.models.NewsResp
import com.example.test1.utils.apikey.APIKEY
import retrofit2.Response
import retrofit2.http.*


interface NewsService {

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query : String,
        @Query("page") page : Int = 1,
        @Query("apiKey") apiKey : String = APIKEY,

    ) : Response<NewsResp>


    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") countrycode : String = "ru",
        @Query("page") page : Int = 1,
        @Query("apiKey") apiKey : String = APIKEY,
        )  : Response<NewsResp>



}

