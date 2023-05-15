package com.convert.usd.aud.currencyconverter.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.convert.usd.aud.currency.R
import com.convert.usd.aud.currency.databinding.ActivityMainBinding
import com.convert.usd.aud.currencyconverter.utill.Constance.Companion.APIKEY
import com.convert.usd.aud.currencyconverter.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var symbols: MutableList<String>

    lateinit var from: String
    lateinit var to: String

    lateinit var currencylist: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        currencylist = resources.getStringArray(R.array.symbols).toList()
        setSpiner()

        binding.convertBtn.setOnClickListener(
            View.OnClickListener {
                callConvert(from, to)
            }
        )

        //convert rate

        callLattest()
        // latest rate

    }

    private fun setSpiner() {

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, currencylist
        )

        binding.spFrom.adapter = arrayAdapter
        binding.spTo.adapter = arrayAdapter

          from = currencylist[0]
            to = currencylist[0]


        binding.spFrom.onItemSelectedListener

        binding.spFrom.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                from = currencylist[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        binding.spTo.onItemSelectedListener

        binding.spTo.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                to = currencylist[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

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

    private fun callConvert(from: String, to: String) {
        mainActivityViewModel.getConvertValue(APIKEY, from, to, 1)
        mainActivityViewModel.convertValue.observe(this) {
            Log.e("latestRate", "" + it.body()?.rateResponse)
            binding.llResult.visibility = View.VISIBLE

            binding.resultTV.text = it.body()?.rateResponse?.value.toString()
        }
    }
}