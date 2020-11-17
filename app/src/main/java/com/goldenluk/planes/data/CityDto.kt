package com.goldenluk.planes.data

import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("location")
    val location: LocationDto
)