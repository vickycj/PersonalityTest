package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("category")
    var category: String = "",
    @SerializedName("question")
    var question: String = "",
    @SerializedName("question_type")
    var questionType: QuestionType = QuestionType()
)