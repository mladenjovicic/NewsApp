package com.mladenjovicic.newsapp.data.model.server

import com.google.gson.annotations.SerializedName


data class ArticlesServerModel(
    @SerializedName("author") var author: String,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("publishedAt") var publishedAt: String,
    @SerializedName("content") var content: String,
    @SerializedName("url") var url: String,
    @SerializedName("urlToImage") var urlToImage: String,
    @SerializedName("source") var source: SourceServerModel
)