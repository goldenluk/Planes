package com.goldenluk.planes.data.dto

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)