package com.mladenjovicic.newsapp.ui.savedNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel
import com.mladenjovicic.newsapp.data.repository.NewsRepository

class SavedNewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {


    var newsLocalLiveData: LiveData<List<ArticlesLocalModel>>? = null



    fun getNewsLocal(): LiveData<List<ArticlesLocalModel>>? {
        newsLocalLiveData = newsRepository.getListLocation()
        return newsLocalLiveData
    }
}