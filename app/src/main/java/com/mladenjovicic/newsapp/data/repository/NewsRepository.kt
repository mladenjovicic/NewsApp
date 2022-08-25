package com.mladenjovicic.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.newsapp.data.model.server.NewsServerModel
import com.mladenjovicic.newsapp.data.model.RequestState
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel
import com.mladenjovicic.newsapp.data.server.RetrofitService

class NewsRepository(
    private val retrofitService: RetrofitService,
    private val localRepository: LocalRepository
) {

    fun getServerNewsEverything(
        query: String? = null,
        sorting: String? = null,
        from: String? = null,
        to: String? = null,
        livedata: MutableLiveData<NewsServerModel>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.getServerNewsEverything(
        query = query,
        sorting = sorting,
        from = from,
        to = to,
        liveData = livedata,
        requestState = requestState
    )

    fun getServerNewsTopHeadlines(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
        domains: String? = null,
        livedata: MutableLiveData<NewsServerModel>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.getServerNewsTopHeadlines(
        country = country,
        category = category,
        sources = sources,
        domains = domains,
        liveData = livedata,
        requestState = requestState
    )


    fun addNewsLocal(
        newsLocal:ArticlesLocalModel
    ){
        localRepository.insertDataNews(newsLocal)
    }

    fun getListLocation(): LiveData<List<ArticlesLocalModel>>? {
        return localRepository.getListNews()!!
    }
}