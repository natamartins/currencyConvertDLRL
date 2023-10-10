package com.conversor.conversosdemoedas.ValueBid

import okhttp3.*
import java.io.IOException

fun buscaBid(callback: (Double) -> Unit) {
    val client = OkHttpClient()
    val request = Request.Builder().url("https://economia.awesomeapi.com.br/last/USD-BRL").build()

    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val json = response.body()?.string()
            val bid =
                json?.split("\"bid\":\"")?.get(1)?.split("\"")?.get(0)?.toDoubleOrNull() ?: 0.0
            callback(bid)
        }

        override fun onFailure(call: Call, e: IOException) {
            callback(0.0)
        }
    })
}
