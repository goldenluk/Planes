package com.goldenluk.planes.data.dto

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("cities")
    val cities: List<CityDto>
)