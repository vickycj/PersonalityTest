package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class Range(
    @SerializedName("from")
    var from: Int = 0,
    @SerializedName("to")
    var to: Int = 0
)