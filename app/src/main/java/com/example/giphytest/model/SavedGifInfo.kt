package com.example.giphytest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.giphytest.utils.Constants
import org.jetbrains.annotations.NotNull


@Entity(tableName = Constants.TABLE_SAVED_GIF)
data class SavedGifInfo(
    val id : String,
    val url: String
){
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id_primary: Int =0
}