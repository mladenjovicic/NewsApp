package com.mladenjovicic.newsapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.newsapp.data.model.NewsModel
import com.mladenjovicic.newsapp.data.model.RequestState
import com.mladenjovicic.newsapp.data.server.RetrofitService

class NewsRepository(private val retrofitService: RetrofitService) {

    fun getServerNewsEverything(
        query: String? = null,
        sorting: String? = null,
        from:String? = null,
        to:String?? = null,
        livedata: MutableLiveData<NewsModel>,
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
        livedata: MutableLiveData<NewsModel>,
        requestState: MutableLiveData<RequestState>
    ) = retrofitService.getServerNewsTopHeadlines(
        country = country,
        category = category,
        sources = sources,
        domains = domains,
        liveData = livedata,
        requestState = requestState
    )
}