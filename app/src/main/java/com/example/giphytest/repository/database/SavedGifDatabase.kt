package com.example.giphytest.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.giphytest.model.SavedGifInfo
import com.example.giphytest.utils.Constants

@Database(entities = [SavedGifInfo::class], version = 1, exportSchema = false)
abstract class SavedGifDatabase : RoomDatabase() {

    abstract fun savedGifDao(): SavedGifInfoDao

    companion object {
        private var instance: SavedGifDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): SavedGifDatabase {
            if (instance == null) {
                synchronized(SavedGifDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(ctx.applicationContext, SavedGifDatabase::class.java, Constants.DATABASE_SAVED_GIF)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance!!
        }
    }

}