package com.android.newsapp.model

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName("status") val status: String,
    @SerializedName("sources") val sources: List<Source>
)