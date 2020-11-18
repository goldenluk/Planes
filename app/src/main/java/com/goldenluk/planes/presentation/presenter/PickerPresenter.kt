package com.goldenluk.planes.presentation.presenter

import android.os.Bundle
import com.goldenluk.planes.data.dto.CityDto
import com.goldenluk.planes.presentation.view.PickerView

private const val TO_CITY_KEY = "to_city"
private const val FROM_CITY_KEY = "from_city"

class PickerPresenter constructor(
    private val view: PickerView
) {

    private var fromCity: CityDto? = null
    private var toCity: CityDto? = null

    fun onFromCardClicked() {
        view.showSelectFromScreen()
    }

    fun onToCardClicked() {
        view.showSelectToScreen()
    }

    fun onSearchButtonClicked() {

    }

    fun onFromCitySet(city: CityDto) {
        fromCity = city
    }

    fun onToCitySet(city: CityDto) {
        toCity = city
    }

    fun onSaveInstanceState(bundle: Bundle) {
        bundle.putSerializable(TO_CITY_KEY, toCity)
        bundle.putSerializable(FROM_CITY_KEY, fromCity)
    }

    fun onCreate(bundle: Bundle) {
        toCity = bundle.getSerializable(TO_CITY_KEY) as? CityDto?
        fromCity = bundle.getSerializable(FROM_CITY_KEY) as? CityDto?
        toCity?.let {
            view.setToCity(it)
        }
        fromCity?.let {
            view.setFromCity(it)
        }
    }
}