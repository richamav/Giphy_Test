package com.example.giphytest.model

import com.google.gson.annotations.SerializedName

data class DownsizedLargePojo (
    @SerializedName("height")
    val height: String? = null,

    @SerializedName("width")
    val width: String? = null,

    @SerializedName("size")
    val size: String? = null,

    @SerializedName("url")
    val url: String? = null
)