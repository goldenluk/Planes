package com.goldenluk.planes.presentation.view

import com.goldenluk.planes.data.dto.CityDto

interface PickerView {

    fun setFromCity(fromCity: CityDto)

    fun setToCity(toCity: CityDto)

    fun showSelectFromScreen()

    fun showSelectToScreen()
}