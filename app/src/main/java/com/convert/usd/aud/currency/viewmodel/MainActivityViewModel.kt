package com.convert.usd.aud.currencyconverter.viewmodel


import LatestResponse
import com.convert.usd.aud.currency.model.convertion.DataResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.convert.usd.aud.currencyconverter.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(private var apiRepository: ApiRepository) : ViewModel() {

    var convertValue = MutableLiveData<Response<DataResponse>>()
    var latestValue = MutableLiveData<Response<LatestResponse>>()

    fun getConvertValue(apikey: String, from: String, to: String, amount: Int) {
        viewModelScope.launch {
            apiRepository.getConvertValue(apikey, from, to, amount).collect() {
                convertValue.postValue(it)
            }

        }
    }

    init {
        // you can call fun from here
    }

    fun getLatestRate(apiKey: String, base: String, symbols: String) {
        viewModelScope.launch {
            apiRepository.getLatestData(apiKey, base, symbols).collect() {
                latestValue.postValue(it)
            }

        }
    }
}