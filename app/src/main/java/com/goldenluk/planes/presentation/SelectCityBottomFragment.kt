package com.goldenluk.planes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.goldenluk.planes.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val IS_FROM_KEY = "is_from"

class SelectCityBottomFragment : BottomSheetDialogFragment() {

    companion object {

        fun newInstance(isFrom: Boolean) = SelectCityBottomFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_FROM_KEY, isFrom)
            }
        }
    }

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
            titleView.text = view.context.getString(R.string.from_hint)
        } else {
            titleView.text = view.context.getString(R.string.to_hint)
        }
    }
}