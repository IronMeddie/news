package com.example.test1.data.db

import androidx.room.*
import com.example.test1.models.Article

@Dao
interface ArctikDao {
    @Query("SELECT * FROM articles")
    fun getAllArt() : List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}