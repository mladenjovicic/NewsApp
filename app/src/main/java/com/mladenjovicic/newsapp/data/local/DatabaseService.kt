package com.mladenjovicic.newsapp.data.local
import android.content.Context
import androidx.room.Room

class DatabaseService private constructor(private val applicationContext: Context){


    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NewsDatabase::class.java,
            "news_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    val newsDAO: DAOAccesNews
    get() = database.newsDAO()


    companion object {
        @Volatile
        private var instance: DatabaseService? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: DatabaseService(context).also { instance = it }
        }
    }
}