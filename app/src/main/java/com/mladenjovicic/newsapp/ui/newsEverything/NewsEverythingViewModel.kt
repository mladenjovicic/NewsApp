package com.mladenjovicic.newsapp.ui.newsEverything

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.newsapp.data.model.server.NewsServerModel
import com.mladenjovicic.newsapp.data.model.RequestState
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel
import com.mladenjovicic.newsapp.data.repository.NewsRepository

class NewsEverythingViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    val requestState = MutableLiveData<RequestState>()
    val newsLiveData = MutableLiveData<NewsServerModel>()

    fun getServerNewsEverything(
        query: String? = null,
        sorting: String? = null,
        from: String? = null,
        to: String? = null,
    ) {
        newsRepository.getServerNewsEverything(
            query = query,
            sorting = sorting,
            from = from,
            to = to,
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