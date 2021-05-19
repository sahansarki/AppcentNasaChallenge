package com.example.appcentnasachallenge.service

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.appcentnasachallenge.model.Camera
import com.example.appcentnasachallenge.model.Rover

//@ProvidedTypeConverter
class Converters {


    @TypeConverter
    fun fromCamera(camera: Camera): List<String> {
        return listOf(camera.name, camera.full_name)
    }

    @TypeConverter
    fun toCamera(list: List<String>): Camera {
        return Camera(list[0], list[1])
    }

    @TypeConverter
    fun fromRover(rover: Rover): List<String> {
        return listOf(rover.name, rover.landing_date, rover.launch_data, rover.status)
    }

    @TypeConverter
    fun toRover(list: List<String>): Rover {
        return Rover(list[0], list[1], list[2], list[3])
    }


}