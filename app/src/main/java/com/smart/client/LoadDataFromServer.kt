package com.smart.client

import androidx.compose.runtime.MutableState
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

fun loadDataFromServer(serverIp: String, serverPort: String, dataString: MutableState<String>) {
    if (serverIp.isEmpty() || serverPort.isEmpty()) {
        return
    }
    val client = OkHttpClient()
    val url = "http://${serverIp}:${serverPort}/room-data"
    val request = Request.Builder()
        .url(url)
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            dataString.value = ""
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) {
            response.use {
                val responseBody = response.body?.string() ?: ""
                if (responseBody.isEmpty()) {
                    return
                }
                dataString.value = responseBody
            }
        }
    })
}
