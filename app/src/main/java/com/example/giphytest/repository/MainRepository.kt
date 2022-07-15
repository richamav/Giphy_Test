package com.example.giphytest.repository

import androidx.lifecycle.MutableLiveData
import com.example.giphytest.model.GiphyInfo
import com.example.giphytest.model.SavedGifInfo
import com.example.giphytest.repository.api.RetrofitApiInterface
import com.example.giphytest.repository.database.SavedGifInfoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainRepository(private val retrofitService: RetrofitApiInterface, private val savedGifDao: SavedGifInfoDao, ) {

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.Main) }

    var trendingGiphyList: MutableLiveData<List<GiphyInfo>> =  MutableLiveData()
    var searchedGiphyList: MutableLiveData<List<GiphyInfo>> =  MutableLiveData()
    var savedGiphyList: MutableLiveData<List<SavedGifInfo>?> =  MutableLiveData()

    fun getTrendingGiphy(){
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val data = retrofitService.getTrendingGiphy()
                trendingGiphyList.postValue(data.data)
            }
        }
    }

    fun getSearchGiphy( str : String){
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val data = retrofitService.getSearchedGiphy(str)
                data.data?.let{searchedGiphyList.postValue(it)}
            }
        }
    }

    /*fun getTrendingGiphy(){
        retrofitService.getTrendingGiphy()
            .enqueue(object : Callback<GiphyResponse?> {
                override fun onResponse(call: Call<GiphyResponse?>?, response: Response<GiphyResponse?>) {
                    if (response.body() != null) {
                        trendingGiphyList.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<GiphyResponse?>?, t: Throwable?) {
                    trendingGiphyList.postValue(null)
                }
            })
    }*/

    /*fun getSearchGiphy( str : String){
        retrofitService.getSearchedGiphy(str)
            .enqueue(object : Callback<GiphyResponse?> {
                override fun onResponse(call: Call<GiphyResponse?>?, response: Response<GiphyResponse?>) {
                    if (response.body() != null) {
                        searchedGiphyList.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<GiphyResponse?>?, t: Throwable?) {
                    searchedGiphyList.postValue(null)
                }
            })
    }*/

    fun getSavedGifFromDatabase() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val data = savedGifDao.getSavedGifs()
                savedGiphyList.postValue(data)
            }
        }
    }

    fun saveGiphyInDatabase(gif: SavedGifInfo) {
        coroutineScope.launch {
            if((withContext(Dispatchers.IO) { savedGifDao.getItemId(gif.id) }).isNullOrEmpty()) {
                savedGifDao.saveGif(gif)
            }
        }
    }

    fun deleteGiphyInDatabase(gif: SavedGifInfo) {
        coroutineScope.launch {
            (withContext(Dispatchers.IO) { savedGifDao.deleteGif(gif) })
            (withContext(Dispatchers.IO) { getSavedGifFromDatabase() })
        }
    }
}