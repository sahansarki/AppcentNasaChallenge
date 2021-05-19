package com.example.appcentnasachallenge.service

import androidx.room.Insert
import androidx.room.Query
import com.example.appcentnasachallenge.model.Photos

interface PhotoDao {

    @Insert
    suspend fun insertAll(vararg photos : Photos)

    @Query("SELECT * FROM photos")
    suspend fun getAllPhotos() : List<Photos>

    @Query("DELETE FROM photos")
    suspend fun deleteAllPhotos()
}