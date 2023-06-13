package com.convert.usd.aud.currencyconverter.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.convert.usd.aud.currency.R
import com.convert.usd.aud.currency.adapter.ExchangeAdapter
import com.convert.usd.aud.currency.databinding.ActivityMainBinding
import com.convert.usd.aud.currency.model.RVmodel
import com.convert.usd.aud.currency.utill.Fun
import com.convert.usd.aud.currency.utill.Fun.addShow
import com.convert.usd.aud.currency.utill.Fun.showBannerAds
import com.convert.usd.aud.currencyconverter.utill.Constance.Companion.APIKEY
import com.convert.usd.aud.currencyconverter.utill.Constance.Companion.APIKEY2
import com.convert.usd.aud.currencyconverter.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var symbols: MutableList<String>
    lateinit var from: String
    lateinit var to: String
    lateinit var lattestList: MutableList<RVmodel>
    lateinit var currencylist: List<String>

    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.logoSign.text = "Forex Exchange Rate"

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        from = "AED"
        to = "AED"

        currencylist =
            resources.getStringArray(com.convert.usd.aud.currency.R.array.symbols).toList()
        setDialog()

        binding.convertBtn.setOnClickListener(View.OnClickListener {
            callConvert(from, to)
        })

        Fun(this, this)
        showBannerAds(binding.adViewContainer, this)

        //convert rate
        lattestList = mutableListOf()
        callLattest()
        // latest rate

    }

    private fun setDialog() {
        binding.llFrom.setOnClickListener(View.OnClickListener {
            dialog = Dialog(this@MainActivity)
            dialog.setContentView(com.convert.usd.aud.currency.R.layout.dialog_searchable_spinner)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            val editText =
                dialog.findViewById<EditText>(R.id.edit_text)
            val cancle =
                dialog.findViewById<ImageView>(R.id.btnCancle)
            val listView =
                dialog.findViewById<ListView>(R.id.list_view)

            val ad_view_container =
                dialog.findViewById<FrameLayout>(R.id.ad_view_container)
            showBannerAds(ad_view_container, this@MainActivity)

            cancle.setOnClickListener(View.OnClickListener { dialog.dismiss() })

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                currencylist
            )
            listView.adapter = adapter
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable) {}
            })

            listView.onItemClickListener =
                OnItemClickListener { parent, view, position, id ->
                    binding.spFrom.text = adapter.getItem(position).toString()
                    from = adapter.getItem(position).toString()
                    dialog.dismiss()
                }
        })

        binding.llTo.setOnClickListener(View.OnClickListener {
            dialog = Dialog(this@MainActivity)
            dialog.setContentView(com.convert.usd.aud.currency.R.layout.dialog_searchable_spinner)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            val cancle =
                dialog.findViewById<ImageView>(R.id.btnCancle)
            val editText =
                dialog.findViewById<EditText>(R.id.edit_text)
            val listView =
                dialog.findViewById<ListView>(R.id.list_view)
            cancle.setOnClickListener(View.OnClickListener { dialog.dismiss() })
            val ad_view_container =
                dialog.findViewById<FrameLayout>(R.id.ad_view_container)
            showBannerAds(ad_view_container, this@MainActivity)

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                currencylist
            )
            listView.adapter = adapter
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable) {}
            })

            listView.onItemClickListener =
                OnItemClickListener { parent, view, position, id ->
                    binding.spTo.text = adapter.getItem(position).toString()
                    to = adapter.getItem(position).toString()
                    dialog.dismiss()
                }
        })

    }

    private fun setSpiner() {
        /*
                val arrayAdapter = ArrayAdapter(
                    this, android.R.layout.simple_spinner_item, currencylist
                )

                binding.spFrom.adapter = arrayAdapter
                binding.spTo.adapter = arrayAdapter

                from = currencylist[0]
                to = currencylist[0]


                binding.spFrom.onItemSelectedListener

                binding.spFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View, position: Int, id: Long
                    ) {
                        from = currencylist[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }


                binding.spTo.onItemSelectedListener

                binding.spTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View, position: Int, id: Long
                    ) {
                        to = currencylist[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }*/

    }

    private fun callLattest() {
        //  symbols = mutableListOf("AED", "CAD", "BDT", "EUR")
        //  symbols.add("INR")

        val ss =
            "AUD" + "," + "EUR" + "," + "CAD" + "," + "GBP" + "," + "CHF" + "," + "JPY" + "," + "NZD" + "," + "CNY"

        mainActivityViewModel.getLatestRate(APIKEY2, "USD", ss)
        mainActivityViewModel.latestValue.observe(this) {
            Log.e("rate", "" + it.body()?.response?.rates.toString())

            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.AUD
                    ), "AUD"
                )
            )
            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.EUR
                    ), "EUR"
                )
            )
            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.CAD
                    ), "CAD"
                )
            )
            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.GBP
                    ), "GBP"
                )
            )
            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.CHF
                    ), "CHF"
                )
            )
            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.JPY
                    ), "JPY"
                )
            )
            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.NZD
                    ), "NZD"
                )
            )
            lattestList.add(
                RVmodel(
                    "USD", String.format(
                        "%.3f", it.body()?.response?.rates?.CNY
                    ), "CNY"
                )
            )

            //Rv set
            val adapter: ExchangeAdapter = ExchangeAdapter(lattestList)
            binding.rvLatestRate.layoutManager = GridLayoutManager(this, 1)
            binding.rvLatestRate.adapter = adapter


        }
    }

    private fun callConvert(from: String, to: String) {

        addShow()
        mainActivityViewModel.getConvertValue(APIKEY, from, to, 1)
        mainActivityViewModel.convertValue.observe(this) {
            Log.e("latestRate", "" + it.body()?.rateResponse)
            binding.llResult.visibility = View.VISIBLE

            binding.resultTV.text = "1 " + from + " = " + String.format(
                "%.3f", it.body()?.rateResponse?.value
            ) + " " + to
        }
    }
}