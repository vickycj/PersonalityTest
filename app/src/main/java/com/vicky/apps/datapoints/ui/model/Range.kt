package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class Range(
    @SerializedName("from")
    var from: Int?,
    @SerializedName("to")
    var to: Int?
)