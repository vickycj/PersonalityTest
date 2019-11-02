package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class Predicate(
    @SerializedName("exactEquals")
    var exactEquals: List<String> = listOf()
)