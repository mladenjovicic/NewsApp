package com.mladenjovicic.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel

@Database(entities = [ArticlesLocalModel::class], version = 2, exportSchema = false)

abstract class NewsDatabase:RoomDatabase() {
    abstract fun newsDAO() : DAOAccesNews
}