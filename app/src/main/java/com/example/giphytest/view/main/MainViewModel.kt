package com.example.giphytest.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.giphytest.model.GiphyInfo
import com.example.giphytest.model.SavedGifInfo
import com.example.giphytest.repository.MainRepository
import com.example.giphytest.repository.api.RetrofitClient
import com.example.giphytest.repository.database.SavedGifDatabase


class MainViewModel (application: Application) : AndroidViewModel(application) {
    private val retrofitService by lazy { RetrofitClient().getRetrofitApiInstance() }
    private val savedGifDao by lazy { SavedGifDatabase.getInstance(application).savedGifDao() }

    private val mainRepository by lazy { MainRepository(retrofitService,savedGifDao) }

    var giphyResponseLiveData: MutableLiveData<List<GiphyInfo>> = MutableLiveData()
    get() { return mainRepository.trendingGiphyList}

    var searchedGiphyResponseLiveData: MutableLiveData<List<GiphyInfo>> = MutableLiveData()
    get() { return mainRepository.searchedGiphyList}

    var savedGiphyLiveData: MutableLiveData<List<SavedGifInfo>?> = MutableLiveData()
        get() { return mainRepository.savedGiphyList}


    fun getTrendyGiphy() = mainRepository.getTrendingGiphy()
    fun getSearchGiphy(str : String) = mainRepository.getSearchGiphy(str)

    fun getSavedGiphy() = mainRepository.getSavedGifFromDatabase()
    fun saveGiphy(savedGifInfo: SavedGifInfo) = mainRepository.saveGiphyInDatabase(savedGifInfo)
    fun deleteGiphy(savedGifInfo: SavedGifInfo) = mainRepository.deleteGiphyInDatabase(savedGifInfo)
}