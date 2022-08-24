package com.mladenjovicic.newsapp.ui.newsEverything

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mladenjovicic.newsapp.data.model.NewsModel
import com.mladenjovicic.newsapp.data.model.RequestState
import com.mladenjovicic.newsapp.data.repository.NewsRepository

class NewsEverythingViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    val requestState = MutableLiveData<RequestState>()
    val newsLiveData = MutableLiveData<NewsModel>()

    fun getServerNewsEverything(
        query: String? = null,
        sorting: String? = null,
        from: String? = null,
        to: String?? = null,
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
}