package com.goldenluk.planes.data.service

import com.goldenluk.planes.data.dto.CitiesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesService {

    @GET("/autocomplete?")
    fun getCities(
        @Query("term") term: String,
        @Query("lang") lang: String = "ru"
    ): Single<CitiesResponse>
}