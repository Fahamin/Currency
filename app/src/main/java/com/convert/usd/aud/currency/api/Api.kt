package com.convert.usd.aud.currencyconverter.api

import DataResponse
import RateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("convert")
    suspend fun getRate(
        @Query("api_key") apiKey: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Int
    ): Response<DataResponse>
}