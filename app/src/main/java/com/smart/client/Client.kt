package com.smart.client

import androidx.compose.runtime.MutableState
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException

fun getDataFromServer(
    serverIp: String,
    serverPort: String,
    dataString: MutableState<String>,
    endpointName: String
) {
    if (serverIp.isEmpty() || serverPort.isEmpty()) {
        return
    }
    val client = OkHttpClient()
    val url = "http://${serverIp}:${serverPort}/${endpointName}"
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

fun postDataToServer(
    serverIp: String,
    serverPort: String,
    bodyString: String,
    endpointName: String
) {
    if (serverIp.isEmpty() || serverPort.isEmpty()) {
        return
    }
    val client = OkHttpClient()
    val url = "http://${serverIp}:${serverPort}/${endpointName}"
    val json = "application/json; charset=utf-8".toMediaType()
    val body: RequestBody = bodyString.toRequestBody(json)
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) {}
    })
}
