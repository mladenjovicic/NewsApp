package com.mladenjovicic.newsapp.data.server

import androidx.lifecycle.MutableLiveData
import com.mladenjovicic.newsapp.data.model.NewsModel
import com.mladenjovicic.newsapp.data.model.RequestState
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RetrofitService(retrofitInstance: RetrofitInstance) {

    private val retrofitInterface =
        retrofitInstance.getRetrofit().create(RetrofitInterface::class.java)

    fun getServerNewsEverything(
        query: String? = null,
        sorting: String? = null,
        from: String? = null,
        to: String?? = null,
        liveData: MutableLiveData<NewsModel>,
        requestState: MutableLiveData<RequestState>
    ) {
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getServerNewsEverything(query, sorting, from, to)

        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }

    fun getServerNewsTopHeadlines(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
        domains: String? = null,
        liveData: MutableLiveData<NewsModel>,
        requestState: MutableLiveData<RequestState>
    ) {
        requestState.postValue(RequestState.pending)
        val call = retrofitInterface.getServerNewsTopHeadlines(country, category, sources, domains)

        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                val body = response.body()
                if (body != null) {
                    liveData.postValue(body)
                    requestState.postValue(RequestState.success)
                } else
                    requestState.postValue(RequestState.failed)
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                requestState.postValue(
                    RequestState(
                        pending = false,
                        successful = false,
                        errorMessage = t.message.toString()
                    )
                )
            }
        })
    }
}