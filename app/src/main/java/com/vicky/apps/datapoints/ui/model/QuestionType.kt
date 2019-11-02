package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class QuestionType(
    @SerializedName("range")
    var range: Range = Range(),
    @SerializedName("condition")
    var condition: Condition = Condition(),
    @SerializedName("options")
    var options: List<String> = listOf(),
    @SerializedName("type")
    var type: String = ""
)