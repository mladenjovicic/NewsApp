package com.mladenjovicic.newsapp.data.model.server

import com.google.gson.annotations.SerializedName

data class SourceServerModel(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String
)