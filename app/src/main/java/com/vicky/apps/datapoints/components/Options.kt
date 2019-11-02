package com.vicky.apps.datapoints.components

import android.content.Context
import android.util.AttributeSet

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.components.adapter.OptionsAdapter
import com.vicky.apps.datapoints.components.model.OptionsData
import com.vicky.apps.datapoints.components.model.QuestionCardData

import kotlinx.android.synthetic.main.component_parent_radio.view.*

class Options(context:Context,optionsData: List<OptionsData>): LinearLayout(context) {

    private val recyclerView: RecyclerView

    private val adapter: OptionsAdapter

    constructor(context: Context,attributeSet: AttributeSet):this(context, listOf(OptionsData("",false)))
    constructor(context: Context):this(context, listOf(OptionsData("",false)))

    init {
        inflate(context, R.layout.component_parent_radio,this)

        recyclerView = options_recycler_view

        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = OptionsAdapter(optionsData)

        recyclerView.adapter = adapter
    }

    fun updateValues(optionsData: List<OptionsData>){
        adapter.updateValues(optionsData)
    }

}