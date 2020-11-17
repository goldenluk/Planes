package com.goldenluk.planes.data

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("cities")
    val cities: List<CityDto>
)