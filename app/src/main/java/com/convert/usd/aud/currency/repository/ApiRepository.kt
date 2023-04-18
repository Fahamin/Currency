package com.convert.usd.aud.currencyconverter.repository

import com.convert.usd.aud.currencyconverter.api.Api
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor(private var api: Api) {

    suspend fun getCalculation(apiKey: String, from: String, to: String, amount: Int) = flow {
        var response = api.getRate(apiKey, from, to, amount)
        emit(response)

    }.catch { e ->
    }


}