package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("if_positive")
    var ifPositive: IfPositive = IfPositive(),
    @SerializedName("predicate")
    var predicate: Predicate = Predicate()
)