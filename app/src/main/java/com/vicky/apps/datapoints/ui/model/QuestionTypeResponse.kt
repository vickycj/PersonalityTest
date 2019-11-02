package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class QuestionTypeResponse(
    @SerializedName("categories")
    var categories: List<String?>?,
    @SerializedName("questions")
    var questions: List<Question?>?
)