package com.vicky.apps.datapoints.components

import android.content.Context

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.components.adapter.OptionsAdapter
import com.vicky.apps.datapoints.components.model.OptionsData
import com.vicky.apps.datapoints.components.model.QuestionCardData

import kotlinx.android.synthetic.main.component_parent_radio.view.*

class Options(context:Context,questionCardData: QuestionCardData): LinearLayout(context) {

    private val recyclerView: RecyclerView

    private val adapter: OptionsAdapter

    companion object {
        val SINGLE_CHOICE_TYPE = 1
    }


    init {
        inflate(context, R.layout.component_parent_radio,this)

        recyclerView = options_recycler_view

        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = OptionsAdapter(questionCardData.options)

        recyclerView.adapter = adapter
    }

    fun updateValues(optionsData: List<OptionsData>){
        adapter.updateValues(optionsData)
    }

}