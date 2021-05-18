package com.example.appcentnasachallenge.model

data class APIRoverModel(
    var photos : ArrayList<Photos>
)

data class Photos(
    val camera : Camera,
    val rover : Rover,
    val img_src : String,
    val earth_date : String
)

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