package com.goldenluk.planes.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.goldenluk.planes.R
import com.goldenluk.planes.data.dto.CityDto
import com.goldenluk.planes.presentation.SelectCityBottomFragment
import com.goldenluk.planes.presentation.presenter.PickerPresenter
import com.goldenluk.planes.presentation.view.PickerView

private const val TO_CITY_KEY = "to_city"
private const val FROM_CITY_KEY = "from_city"

class PickerActivity : AppCompatActivity(), PickerView {

    private lateinit var fromCityCard: CardView
    private lateinit var fromCityView: TextView
    private lateinit var toCityCard: CardView
    private lateinit var toCityView: TextView
    private lateinit var searchButton: Button
    private lateinit var presenter: PickerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker)
        fromCityCard = findViewById(R.id.from_card)
        fromCityView = findViewById(R.id.from_city)
        toCityCard = findViewById(R.id.to_card)
        toCityView = findViewById(R.id.to_city)
        searchButton = findViewById(R.id.search_button)
        presenter = PickerPresenter(this)
        savedInstanceState?.let {
            presenter.onCreate(it)
        }
    }

    override fun onStart() {
        super.onStart()
        setListeners()
    }

    override fun onStop() {
        super.onStop()
        clearListeners()
    }

    override fun setFromCity(fromCity: CityDto) {
        fromCityView.text = fromCity.fullName
        presenter.onFromCitySet(fromCity)
    }

    override fun setToCity(toCity: CityDto) {
        toCityView.text = toCity.fullName
        presenter.onToCitySet(toCity)
    }

    override fun showSelectFromScreen() {
        val selectFragment =
            SelectCityBottomFragment.newInstance(
                isFrom = true
            )
        selectFragment.show(supportFragmentManager, "")
    }

    override fun showSelectToScreen() {
        val selectFragment =
            SelectCityBottomFragment.newInstance(
                isFrom = false
            )
        selectFragment.show(supportFragmentManager, "")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun showMap(fromCity: CityDto, toCity: CityDto) {
        val intent = Intent(this, MapActivity::class.java)
            .putExtra(TO_CITY_KEY, toCity)
            .putExtra(FROM_CITY_KEY, fromCity)
        startActivity(intent)
    }

    private fun setListeners() {
        fromCityCard.setOnClickListener {
            presenter.onFromCardClicked()
        }
        toCityCard.setOnClickListener {
            presenter.onToCardClicked()
        }
        searchButton.setOnClickListener {
            presenter.onSearchButtonClicked()
        }
    }

    private fun clearListeners() {
        fromCityCard.setOnClickListener(null)
        toCityCard.setOnClickListener(null)
        searchButton.setOnClickListener(null)
    }
}