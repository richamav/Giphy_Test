package com.example.giphytest.model


import com.google.gson.annotations.SerializedName

class GiphyResponse {
    @SerializedName("data")
    var data: List<GiphyInfo>? = null

    @SerializedName("pagination")
    var pagination: PaginationPojo? = null

    @SerializedName("meta")
    var meta: MetaPojo? = null
}

class GiphyInfo {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("id")
    var id: String=""

    @SerializedName("url")
    var url: String? = null

    @SerializedName("bitly_gif_url")
    var bitlyGifUrl: String? = null

    @SerializedName("bitly_url")
    var bitlyUrl: String? = null

    @SerializedName("embed_url")
    var embedUrl: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("source")
    var source: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("rating")
    var rating: String? = null

    @SerializedName("content_url")
    var contentUrl: String? = null

    @SerializedName("source_tld")
    var sourceTld: String? = null

    @SerializedName("source_post_url")
    var sourcePostUrl: String? = null

    @SerializedName("is_sticker")
    var isSticker: Int? = null

    @SerializedName("import_datetime")
    var importDatetime: String? = null

    @SerializedName("trending_datetime")
    var trendingDatetime: String? = null

    @SerializedName("images")
    var images: GiphyImagesPojo? = null

    var full: Boolean = false
}

class MetaPojo{
    @SerializedName("status")
    var status: Int =0

    @SerializedName("msg")
    var msg: String? = null

    @SerializedName("responseId")
    var responseId: String? = null

    var additionalProperties: HashMap<String, Any> = HashMap<String, Any>()
}

class PaginationPojo{
    @SerializedName("totalCount")
    var totalCount: Int =0
    @SerializedName("count")
    var count: Int =0
    @SerializedName("offset")
    var offset: Int =0
}
