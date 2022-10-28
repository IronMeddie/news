package com.example.test1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test1.models.Article


@Database(entities = [Article::class], version = 1, exportSchema = true)
abstract class ArticleDB: RoomDatabase() {
    abstract fun getarticleDao() : ArctikDao
}