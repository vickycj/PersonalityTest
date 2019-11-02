package com.vicky.apps.datapoints.data.remote


import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("/sparknetworks/coding_exercises_options/master/personality_test/database/personality_test.json")
    fun getDataFromService(): Single<List<Any>>
}
