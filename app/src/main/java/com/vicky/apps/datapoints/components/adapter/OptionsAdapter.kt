package com.vicky.apps.datapoints.components.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.components.model.OptionsData

class OptionsAdapter(var options:List<OptionsData>):
    RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.component_radio_button,parent,false)
        return OptionsViewHolder(v)
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.radioButton.text = options[position].text
        if(!options[position].checked) {
            holder.radioButton.isChecked = false
        }
    }



    inner class OptionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val radioButton:RadioButton = itemView.findViewById(R.id.radioButton)

        init {
            radioButton.setOnClickListener {
                changeCheckedStatus()
                options[adapterPosition].checked = true
                notifyDataSetChanged()
            }
        }
    }

    private fun changeCheckedStatus(){
        options.forEach {
            it.checked = false
        }
    }

    fun updateValues(options: List<OptionsData>){
        this.options = options
        notifyDataSetChanged()
    }

}