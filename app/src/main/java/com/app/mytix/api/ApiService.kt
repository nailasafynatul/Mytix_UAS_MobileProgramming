package com.app.mytix.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("v1/1b8ebe1b-da4d-451c-86c4-b4b1b8682f80")
    fun getPrices(): Call<PriceResponse>
}