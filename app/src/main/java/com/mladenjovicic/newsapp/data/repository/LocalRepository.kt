package com.mladenjovicic.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.mladenjovicic.newsapp.data.local.DatabaseService
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LocalRepository(
    private val databaseService: DatabaseService
) {
    var newsLocalLiveData: LiveData<List<ArticlesLocalModel>>? = null


    fun insertDataNews(
        newsLocalModel: ArticlesLocalModel
    ) {
        CoroutineScope(IO).launch {

            databaseService.newsDAO.insertNews(newsLocalModel)
        }
    }

    fun getListNews(): LiveData<List<ArticlesLocalModel>>? {
        newsLocalLiveData = databaseService.newsDAO.getUsersList()
        return newsLocalLiveData
    }


}