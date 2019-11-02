package com.vicky.apps.datapoints.components.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import kotlinx.android.synthetic.main.component_radio_button.view.*

class OptionsAdapter(var options:List<String>):
    RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.component_radio_button,parent,false)
        return OptionsViewHolder(v)
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.radioButton.text = options[position]
    }



    inner class OptionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val radioButton:RadioButton = itemView.findViewById(R.id.radioButton)

        init {
            radioButton.setOnClickListener {

            }
        }
    }

}