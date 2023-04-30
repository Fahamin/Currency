package com.convert.usd.aud.currencyconverter.repository

import android.util.Log
import com.convert.usd.aud.currencyconverter.api.Api
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor(private var api: Api) {

    suspend fun getConvertValue(apiKey: String, from: String, to: String, amount: Int) = flow {
        var response = api.getRate(apiKey, from, to, amount)
        emit(response)
        Log.e("rate", "" + response)

    }.catch { e ->
        Log.e("rate", "" + e.message)

    }

    suspend fun getLatestData(apiKey: String, base: String, symbols: String) = flow {
        var response = api.getLattestRate(apiKey, base, symbols)
        emit(response)
        Log.e("LatestRate", "" + response)
    }.catch { e ->
        Log.e("rate", "" + e.message)

    }
}