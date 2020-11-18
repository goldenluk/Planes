package com.goldenluk.planes.data

import com.goldenluk.planes.data.dto.CitiesResponse
import com.goldenluk.planes.data.service.CitiesService
import com.goldenluk.planes.domain.CitiesRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CitiesRepositoryImpl constructor(
    private val citiesService: CitiesService
) : CitiesRepository {

    override fun getCities(term: String): Single<CitiesResponse> {
        return citiesService.getCities(term)
            .subscribeOn(Schedulers.io())
    }
}