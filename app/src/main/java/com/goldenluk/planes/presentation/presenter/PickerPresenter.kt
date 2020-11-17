package com.goldenluk.planes.presentation.presenter

import com.goldenluk.planes.presentation.view.PickerView

class PickerPresenter constructor(
    private val view: PickerView
) {

    fun onFromCardClicked() {
        view.showSelectFromScreen()
    }

    fun onToCardClicked() {
        view.showSelectToScreen()
    }

    fun onSearchButtonClicked() {

    }
}