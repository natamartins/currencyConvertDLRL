package com.conversor.conversosdemoedas.Api

import retrofit2.Call
import retrofit2.http.GET

interface ExchangeRateService {
    @GET("/last/USD-BRL")
    fun getExchangeRate(): Call<ExchangeRateResponse>
}