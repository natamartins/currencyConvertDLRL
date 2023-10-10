package com.conversor.conversosdemoedas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import com.conversor.conversosdemoedas.Api.CoinGeckoService
import com.conversor.conversosdemoedas.Api.CryptoCurrency
import com.conversor.conversosdemoedas.Api.ExchangeRateResponse
import com.conversor.conversosdemoedas.ValueBid.buscaBid
import com.conversor.conversosdemoedas.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = Intent(this, ValueOfConvertion::class.java)

        val retrofit = Retrofit.Builder().baseUrl("https://api.coingecko.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(CoinGeckoService::class.java)
        val call = service.getTopCryptos()

        call.enqueue(object : Callback<List<CryptoCurrency>> {
            override fun onResponse(
                call: Call<List<CryptoCurrency>>, response: Response<List<CryptoCurrency>>
            ) {
                if (response.isSuccessful) {
                    val cryptoList = response.body()
//                    if (cryptoList != null) {
//                        for (crypto in cryptoList) {
//                            val currencyPrice = crypto.current_price
//                            // colocar os date na navbar
//                            println("CurrencyPrice ==> $currencyPrice")
//                        }
//                    }


                    radioGroup = findViewById(R.id.radioGroup)
                    radioGroup.setOnCheckedChangeListener { _, checkedId ->
                        val radioButton1 = findViewById<RadioButton>(R.id.radius_cash)
                        val radioButton2 = findViewById<RadioButton>(R.id.radius_card)

                        buscaBid { bid ->
                            runOnUiThread {
                                val bidFormat = String.format("%.2f", bid).toDouble()
                                binding.btnConverte.setOnClickListener {
                                    if (checkedId == radioButton1.id) {
                                        val inputDl =
                                            findViewById<TextInputEditText>(R.id.input_dolar)
                                        val inputTx =
                                            findViewById<TextInputEditText>(R.id.input_taxa)
                                        val dl = inputDl.text.toString().toDouble()
                                        val tx = inputTx.text.toString().toDouble()
                                        val taxaIOF = 1.1 / 100
                                        val impost = tx / 100

                                        val same = (dl * bidFormat) + impost + taxaIOF
                                        val formatSame = String.format("%.2f", same)
                                        val formatBid = String.format("%.2f", bidFormat)
                                        val formatTaxa = String.format("%.1f", impost)

                                        i.putExtra("valueSame", formatSame)
                                        i.putExtra("valueDola", formatBid)
                                        i.putExtra("valueTaxa", formatTaxa)
                                        i.putExtra("tipe", "Dinheiro")
                                        startActivity(i)
//                                        println("Cash $formatSame")

                                    } else if (checkedId == radioButton2.id) {
                                        val inputDl =
                                            findViewById<TextInputEditText>(R.id.input_dolar)
                                        val inputTx =
                                            findViewById<TextInputEditText>(R.id.input_taxa)
                                        val dl = inputDl.text.toString().toDouble()
                                        val tx = inputTx.text.toString().toDouble()
                                        val taxaIOF = 6.3 / 100
                                        val impost = tx / 100

                                        val same = (dl * bidFormat) + impost + taxaIOF
                                        val formatSame = String.format("%.2f", same)
                                        val formatBid = String.format("%.2f", bidFormat)
                                        val formatTaxa = String.format("%.1f", impost)

                                        i.putExtra("valueSame", formatSame)
                                        i.putExtra("valueDola", formatBid)
                                        i.putExtra("valueTaxa", formatTaxa)
                                        i.putExtra("tipe", "Catão")
                                        startActivity(i)
//                                        println("Card $formatSame")

                                    }

                                }
                            }
                        }
                    }
                } else {
                    // Lidar com erros de resposta
                }
            }

            override fun onFailure(call: Call<List<CryptoCurrency>>, t: Throwable) {
                // Lidar com erros de comunicação ou exceções
            }
        })
    }

}