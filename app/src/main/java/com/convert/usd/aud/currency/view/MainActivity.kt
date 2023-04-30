package com.convert.usd.aud.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.convert.usd.aud.currency.databinding.ActivityMainBinding
import com.convert.usd.aud.currencyconverter.utill.Constance.Companion.APIKEY
import com.convert.usd.aud.currencyconverter.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormatSymbols

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var symbols: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]


        callConvert()
        //convert rate

        callLattest()
        // latest rate

    }

    private fun callLattest() {
      //  symbols = mutableListOf("AED", "CAD", "BDT", "EUR")
        //  symbols.add("INR")

        var ss = "AED" + "," + "BDT" + "," + "CAD"
        mainActivityViewModel.getLatestRate(APIKEY, "USD", ss)
        mainActivityViewModel.latestValue.observe(this) {
            Log.e("rate", "" + it.body()?.response)
        }
    }

    private fun callConvert() {
        mainActivityViewModel.getConvertValue(APIKEY, "USD", "BDT", 9)
        mainActivityViewModel.convertValue.observe(this) {
            Log.e("latestRate", "" + it.body()?.rateResponse)
        }
    }
}