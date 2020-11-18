package com.goldenluk.planes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldenluk.planes.R
import com.goldenluk.planes.data.dto.CityDto

class CitiesAdapter(var data: List<CityDto> = mutableListOf()) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.city_full_name)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cities_item, viewGroup, false)
        view.setOnClickListener(onClickListener)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = data[position].fullName
    }

    override fun getItemCount() = data.size
}