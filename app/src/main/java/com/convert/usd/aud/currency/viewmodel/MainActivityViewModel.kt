package com.convert.usd.aud.currencyconverter.viewmodel


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

    var value = MutableLiveData<Response<DataResponse>>()

    fun getRate(apikey: String, from: String, to: String, amount: Int) {
        viewModelScope.launch {
            apiRepository.getCalculation(apikey, from, to, amount).collect() {
                value.postValue(it)
            }

        }
    }
}