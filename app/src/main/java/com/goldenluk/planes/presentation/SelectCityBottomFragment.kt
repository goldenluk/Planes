package com.goldenluk.planes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldenluk.planes.R
import com.goldenluk.planes.data.CitiesRepositoryImpl
import com.goldenluk.planes.data.RetrofitProvider
import com.goldenluk.planes.data.dto.CitiesResponse
import com.goldenluk.planes.data.service.CitiesService
import com.goldenluk.planes.domain.CitiesRepository
import com.goldenluk.planes.presentation.activity.PickerActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

private const val IS_FROM_KEY = "is_from"
private const val SEARCH_DEBOUNCE = 300L

class SelectCityBottomFragment : BottomSheetDialogFragment() {

    companion object {

        fun newInstance(isFrom: Boolean) = SelectCityBottomFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_FROM_KEY, isFrom)
            }
        }
    }

    // Выглядит не оч, репа во фрагменте. Но я не вижу смысла городить здесь еще презентер и сложные приседания,
    // ради того, чтобы получить подсказки с городами. Причиной тому - это не будет дорабатываться и масштабироваться
    private lateinit var repository: CitiesRepository

    private lateinit var adapter: CitiesAdapter
    private lateinit var recycler: RecyclerView
    private var isFrom: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.bottom_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleView = view.findViewById<TextView>(R.id.select_title)
        if (arguments?.getBoolean(IS_FROM_KEY) ?: return) {
            isFrom = true
            titleView.text = view.context.getString(R.string.from_hint)
        } else {
            isFrom = false
            titleView.text = view.context.getString(R.string.to_hint)
        }
        // И создавать сервис во фрагменте странно. Но и тащить di фреймворк ради двух экранов - не оч
        repository = CitiesRepositoryImpl(RetrofitProvider.provideRetrofit().create(CitiesService::class.java))
        initTextChangeListener()
        initRecycler()
    }

    private fun initTextChangeListener() {
        val editText = view?.findViewById<EditText>(R.id.city_enter) ?: return
        SearchObservable.fromView(editText)
            .debounce(SEARCH_DEBOUNCE, TimeUnit.MILLISECONDS)
            .filter {
                !it.isBlank()
            }
            .distinctUntilChanged()
            .switchMap {
                repository.getCities(it).toObservable()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                },
                onNext = {
                    onCitiesLoaded(it)
                }
            )
    }

    private fun initRecycler() {
        recycler = view?.findViewById(R.id.cities_list) ?: return
        adapter = CitiesAdapter(emptyList())
        // Можно сделать гораздо красивее на лайвдатах, биндинге
        adapter.onClickListener = View.OnClickListener {
            val position = recycler.getChildLayoutPosition(it)
            val item = adapter.data[position]
            val activity = activity as PickerActivity
            if (isFrom) {
                activity.setFromCity(item)
            } else {
                activity.setToCity(item)
            }
            this.dismiss()
        }
        recycler.adapter = adapter
    }

    private fun onCitiesLoaded(citiesResponse: CitiesResponse) {
        adapter.data = citiesResponse.cities
        adapter.notifyDataSetChanged()
    }
}