package com.example.giphytest.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.giphytest.model.SavedGifInfo
import com.example.giphytest.utils.Constants


@Dao
interface SavedGifInfoDao {

    @Query("Select * from ${Constants.TABLE_SAVED_GIF}")
    suspend fun getSavedGifs(): List<SavedGifInfo>?

    @Insert(onConflict = REPLACE)
    suspend fun saveGif(gif : SavedGifInfo)

    @Delete
    suspend fun deleteGif(gif: SavedGifInfo) : Int

    @Query("Select id  from  ${Constants.TABLE_SAVED_GIF}  WHERE id = :id LIMIT 1")
    fun getItemId(id: String): String?
}