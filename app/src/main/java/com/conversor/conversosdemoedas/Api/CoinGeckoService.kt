package com.conversor.conversosdemoedas.Api

import retrofit2.Call
import retrofit2.http.GET

interface CoinGeckoService {
    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=false&locale=en")
    fun getTopCryptos(): Call<List<CryptoCurrency>>
}