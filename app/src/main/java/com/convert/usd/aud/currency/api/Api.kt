package com.convert.usd.aud.currencyconverter.api

import com.convert.usd.aud.currency.model.convertion.DataResponse
import LatestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.DateFormatSymbols

interface Api {

    @GET("convert")
    suspend fun getRate(
        @Query("api_key") apiKey: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Int
    ): Response<DataResponse>


    @GET("latest")
    suspend fun getLattestRate(
        @Query("api_key") apiKey: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String,
    ): Response<LatestResponse>

}