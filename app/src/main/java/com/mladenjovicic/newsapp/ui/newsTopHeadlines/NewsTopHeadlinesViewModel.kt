package com.mladenjovicic.newsapp.ui.newsTopHeadlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.newsapp.data.model.server.NewsServerModel
import com.mladenjovicic.newsapp.data.model.RequestState
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel
import com.mladenjovicic.newsapp.data.repository.NewsRepository

class NewsTopHeadlinesViewModel (private val newsRepository: NewsRepository) : ViewModel(){


    val requestState = MutableLiveData<RequestState>()
    val newsLiveData = MutableLiveData<NewsServerModel>()


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

    fun addNewsLocal(
        author: String?,
        title: String?,
        description: String?,
        publishedAt: String?,
        content: String?,
        url: String?,
        urlToImage: String?,
        sourceId: String?,
        sourceName: String?,
        timeStampSave: String?
    ) {
        val newsLocalLiveData = ArticlesLocalModel(
            author = author,
            title = title,
            description = description,
            publishedAt = publishedAt,
            content = content,
            url = url,
            urlToImage = urlToImage,
            sourceId = sourceId,
            sourceName=sourceName,
            timeStampSave=timeStampSave
        )
        newsRepository.addNewsLocal(newsLocalLiveData)
    }
}