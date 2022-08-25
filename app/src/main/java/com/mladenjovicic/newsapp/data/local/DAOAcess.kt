package com.mladenjovicic.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel

@Dao
interface DAOAccesNews{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsLocalModel: ArticlesLocalModel)

    @Query("select * from articles")
    fun getUsersList(): LiveData<List<ArticlesLocalModel>>
}