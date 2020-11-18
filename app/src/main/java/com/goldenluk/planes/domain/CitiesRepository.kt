package com.goldenluk.planes.domain

import com.goldenluk.planes.data.dto.CitiesResponse
import io.reactivex.Single

interface CitiesRepository {

    fun getCities(term: String): Single<CitiesResponse>
}