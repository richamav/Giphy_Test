package com.example.giphytest.utils

class Constants {

    companion object{

        const val COLUMN_COUNT = 2
        const val API_KEY = "O5I5szQVoLZCAJoweHfWDszrjwnUbOCl"
        const val BASE_URL = "https://api.giphy.com"
        const val TRENDING_URL = "/v1/gifs/trending?api_key=$API_KEY"
        const val SEARCH_URL = "/v1/gifs/search?api_key=$API_KEY"

        const val TABLE_SAVED_GIF = "tbl_saved_gif"
        const val DATABASE_SAVED_GIF ="gif_database"
    }

}