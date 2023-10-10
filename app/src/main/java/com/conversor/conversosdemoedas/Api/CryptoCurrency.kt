package com.conversor.conversosdemoedas.Api

data class CryptoCurrency(
    val id: String,
    val symbol: String,
    val current_price: Number,
    val price_change_percentage_24h: Number
)
