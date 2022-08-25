package com.mladenjovicic.newsapp.data.model.server

import com.google.gson.annotations.SerializedName

data class NewsServerModel(
    @SerializedName("status") var status: String,
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String,
    @SerializedName("totalResults") var totalResults: Int,
    @SerializedName("articles") var articles: List<ArticlesServerModel>
    )

