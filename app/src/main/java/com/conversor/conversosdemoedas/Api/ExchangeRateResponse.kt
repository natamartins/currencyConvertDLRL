package com.conversor.conversosdemoedas.Api

data class ExchangeRateResponse(
    val USDBRL: ExchangeRateData
)

data class ExchangeRateData(
    val bid: Double
)