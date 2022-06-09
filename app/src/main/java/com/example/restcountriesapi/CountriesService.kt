package com.example.restcountriesapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesService {
    @GET("all?")
    fun getCountryData(@Query("fields") fields: String): Call<List<CountriesResponse>>
}