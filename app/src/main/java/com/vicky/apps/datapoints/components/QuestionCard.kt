package com.vicky.apps.datapoints.components

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.components.model.OptionsData
import com.vicky.apps.datapoints.components.model.QuestionCardData
import kotlinx.android.synthetic.main.component_question_card.view.*

class QuestionCard(context: Context): CardView(context) {

   private var itemView:View

    init {
        itemView = View.inflate(context, R.layout.component_question_card,this)
        setQuestion(QuestionCardData("",1,listOf(OptionsData("",false))))
    }


    fun setQuestion(questionCardData: QuestionCardData){
        itemView.questionText.text = questionCardData.question
        itemView.optionsView.updateValues(questionCardData.options)
    }
}