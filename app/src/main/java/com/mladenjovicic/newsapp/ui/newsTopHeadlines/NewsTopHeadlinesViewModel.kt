package com.mladenjovicic.newsapp.ui.newsTopHeadlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.newsapp.data.model.NewsModel
import com.mladenjovicic.newsapp.data.model.RequestState
import com.mladenjovicic.newsapp.data.repository.NewsRepository

class NewsTopHeadlinesViewModel (private val newsRepository: NewsRepository) : ViewModel(){


    val requestState = MutableLiveData<RequestState>()
    val newsLiveData = MutableLiveData<NewsModel>()


    fun getServerNewsTopHeadlines(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
        domains: String? = null,
    ) {
        newsRepository.getServerNewsTopHeadlines(
            country = country,
            category = category,
            sources = sources,
            domains = domains,
            livedata = newsLiveData,
            requestState = requestState
        )
    }
}