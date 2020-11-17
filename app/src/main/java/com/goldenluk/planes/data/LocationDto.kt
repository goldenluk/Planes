package com.goldenluk.planes.data

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)