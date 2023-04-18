package com.convert.usd.aud.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.convert.usd.aud.currency.databinding.ActivityMainBinding
import com.convert.usd.aud.currencyconverter.utill.Constance.Companion.APIKEY
import com.convert.usd.aud.currencyconverter.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.getRate(APIKEY,"USD","AUD",9)
        mainActivityViewModel.value.observe(this){
            Log.e("rate",""+it)
        }
    }
}