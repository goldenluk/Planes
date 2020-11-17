package com.goldenluk.planes.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.goldenluk.planes.R
import com.goldenluk.planes.presentation.view.PickerView

class PickerActivity : AppCompatActivity(), PickerView {

    private lateinit var fromCityCard: CardView
    private lateinit var fromCityView: TextView
    private lateinit var toCityCard: CardView
    private lateinit var toCityView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker)
    }

    override fun setFromCity(fromCity: String) {

    }

    override fun setToCity(toCity: String) {

    }
}