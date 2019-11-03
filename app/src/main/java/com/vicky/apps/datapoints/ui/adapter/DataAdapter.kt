package com.vicky.apps.datapoints.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.components.QuestionCard
import com.vicky.apps.datapoints.components.model.QuestionCardData


class DataAdapter constructor(var data:List<QuestionCardData>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.questionCard.setQuestion(data[position])
    }

    fun updateData(data: List<QuestionCardData>){
        this.data = data
        notifyDataSetChanged()
    }
    class DataViewHolder(v:View): RecyclerView.ViewHolder(v){
        private val questionCardParent: LinearLayout = v.findViewById(R.id.questionCardViewParent)
        var questionCard:QuestionCard = QuestionCard(v.context)
        init {
            questionCardParent.addView(questionCard)
        }
    }
}