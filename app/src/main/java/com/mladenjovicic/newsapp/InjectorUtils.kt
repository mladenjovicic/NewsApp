package com.mladenjovicic.newsapp

import android.content.Context
import com.mladenjovicic.newsapp.data.local.DatabaseService
import com.mladenjovicic.newsapp.data.repository.LocalRepository
import com.mladenjovicic.newsapp.ui.newsEverything.NewsEverythingViewModel
import com.mladenjovicic.newsapp.data.repository.NewsRepository
import com.mladenjovicic.newsapp.data.server.RetrofitInstance
import com.mladenjovicic.newsapp.data.server.RetrofitService
import com.mladenjovicic.newsapp.ui.newsTopHeadlines.NewsTopHeadlinesViewModel
import com.mladenjovicic.newsapp.ui.savedNews.SavedNewsViewModel

object InjectorUtils {

    fun getContext(): Context = NewsApplication.instance.applicationContext

    private val retrofitInstance by lazy { RetrofitInstance() }

    private val retrofitService by lazy { RetrofitService(retrofitInstance) }

    private fun getDatabaseService() = DatabaseService.getInstance(getContext())

    private val localRepository by lazy { LocalRepository(getDatabaseService()) }

    private val newsRepository by lazy { NewsRepository(retrofitService, localRepository) }

    fun getNewsEverythingViewModel() = NewsEverythingViewModel(newsRepository)

    fun getNewsTopHeadlinesViewModel() = NewsTopHeadlinesViewModel(newsRepository)

    fun getSavedNewsViewModel() = SavedNewsViewModel(newsRepository)

}