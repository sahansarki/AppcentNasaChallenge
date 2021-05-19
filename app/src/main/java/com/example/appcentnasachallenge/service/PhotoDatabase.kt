package com.example.appcentnasachallenge.service

import android.content.Context
import androidx.room.*
import com.example.appcentnasachallenge.model.Photos



//@Database(entities = arrayOf(Photos::class), version = 1)
//@TypeConverters(Converters::class)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao() : PhotoDao

    companion object {

        @Volatile
        private var instance : PhotoDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, PhotoDatabase::class.java, "photodatabase"
        ).build()
        // addTypeConverter(Converters::class).
    }
}