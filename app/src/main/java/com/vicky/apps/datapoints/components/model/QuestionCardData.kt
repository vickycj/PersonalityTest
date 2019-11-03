package com.vicky.apps.datapoints.components.model

data class QuestionCardData(
    var question:String,
    var type:Int,
    var options:List<OptionsData>,
    var category:String
)