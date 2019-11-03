package com.vicky.apps.datapoints.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.components.model.QuestionCardData
import kotlinx.android.synthetic.main.component_question_card.view.*

class QuestionCard(context: Context): CardView(context) {

   private var itemView:View =  LayoutInflater.from(context).inflate(R.layout.component_question_card, this)

    private val options: Options = Options(context)

    constructor(context: Context,attributeSet: AttributeSet):this(context)

    init {
        itemView.optionsViewParent.addView(options)
        this.radius = 2.0f
        this.cardElevation = 5.0f
    }

    fun setQuestion(questionCardData: QuestionCardData){
        itemView.questionText.text = questionCardData.question
        options.updateValues(questionCardData.options)
    }
}