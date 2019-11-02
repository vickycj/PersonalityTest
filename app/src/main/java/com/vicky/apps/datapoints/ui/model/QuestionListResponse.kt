package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class QuestionListResponse(
    @SerializedName("categories")
    var categories: List<String> = listOf(),
    @SerializedName("questions")
    var questions: List<Question> = listOf()
)