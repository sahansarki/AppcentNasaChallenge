package com.example.appcentnasachallenge.model

import androidx.room.*
import com.example.appcentnasachallenge.service.Converters

data class APIRoverModel(
    var photos : ArrayList<Photos>
)
@Entity
data class Photos(

    @ColumnInfo(name = "camera")
    @TypeConverters(Converters::class)
    val camera : Camera,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "rover")
    val rover : Rover,
    @ColumnInfo(name = "img_src")
    val img_src : String,
    @ColumnInfo(name = "earth_date")
    val earth_date : String,

    @PrimaryKey(autoGenerate = true)
    val primaryKey : Int
) {

}

data class Camera(
    val name : String,
    val full_name : String
)

data class Rover(
    val name : String,
    val landing_date : String,
    val launch_data : String,
    val status : String

)

