package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class QuestionType(
    @SerializedName("range")
    var range: Range?,
    @SerializedName("condition")
    var condition: Condition?,
    @SerializedName("options")
    var options: List<String?>?,
    @SerializedName("type")
    var type: String?
)