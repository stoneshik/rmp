package com.smart.client

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.MutableState

class DataLoader {
    private val DATA_ROOM_MAX_SECONDS: Int = 10
    private val DATA_FUNCTION_HUMIDITY_SECONDS: Int = 10
    private val DATA_FUNCTION_LIGHTS_SECONDS: Int = 10
    private val DATA_FUNCTION_TEMPERATURE_SECONDS: Int = 10

    private var dataRoomLivingSeconds: Int = 0
    private var dataFunctionHumidityLivingSeconds: Int = 0
    private var dataFunctionLightsLivingSeconds: Int = 0
    private var dataFunctionTemperatureLivingSeconds: Int = 0

    fun updateDataStringsIfLifetimeExceed(
        serverIp: MutableState<String>,
        serverPort: MutableState<String>,
        isNeedUpdateDataRoomsString: MutableState<Boolean>,
        isNeedUpdateDataHumidityString: MutableState<Boolean>,
        isNeedUpdateDataLightsString: MutableState<Boolean>,
        isNeedUpdateDataTemperatureString: MutableState<Boolean>
    ) {
        /*dataRoomLivingSeconds++
        if (dataRoomLivingSeconds > DATA_ROOM_MAX_SECONDS) {
            dataRoomLivingSeconds = 0
            loadDataFromServer(
                serverIp = serverIp.value,
                serverPort = serverPort.value,
                dataString = dataRoomsString
            )
        }*/
        dataRoomLivingSeconds++
        dataFunctionHumidityLivingSeconds++
        dataFunctionLightsLivingSeconds++
        dataFunctionTemperatureLivingSeconds++
        if (dataRoomLivingSeconds > DATA_ROOM_MAX_SECONDS) {
            dataRoomLivingSeconds = 0
            updateFlag(isNeedUpdateDataRoomsString)
        }
        if (dataFunctionHumidityLivingSeconds > DATA_FUNCTION_HUMIDITY_SECONDS) {
            dataFunctionHumidityLivingSeconds = 0
            updateFlag(isNeedUpdateDataHumidityString)
        }
        if (dataFunctionLightsLivingSeconds > DATA_FUNCTION_LIGHTS_SECONDS) {
            dataFunctionLightsLivingSeconds = 0
            updateFlag(isNeedUpdateDataLightsString)
        }
        if (dataFunctionTemperatureLivingSeconds > DATA_FUNCTION_TEMPERATURE_SECONDS) {
            dataFunctionTemperatureLivingSeconds = 0
            updateFlag(isNeedUpdateDataTemperatureString)
        }
    }

    private fun updateFlag(flag: MutableState<Boolean>) {
        if (flag.value) {
            flag.value = false
        }
        flag.value = true
    }
}


fun scheduleDataLoading(
    serverIp: MutableState<String>,
    serverPort: MutableState<String>,
    isNeedUpdateDataRoomsString: MutableState<Boolean>,
    isNeedUpdateDataHumidityString: MutableState<Boolean>,
    isNeedUpdateDataLightsString: MutableState<Boolean>,
    isNeedUpdateDataTemperatureString: MutableState<Boolean>
) {
    val dataLoader = DataLoader()
    val mainHandler = Handler(Looper.getMainLooper())
    mainHandler.post(object : Runnable {
        override fun run() {
            dataLoader.updateDataStringsIfLifetimeExceed(
                serverIp = serverIp,
                serverPort = serverPort,
                isNeedUpdateDataRoomsString = isNeedUpdateDataRoomsString,
                isNeedUpdateDataHumidityString = isNeedUpdateDataHumidityString,
                isNeedUpdateDataLightsString = isNeedUpdateDataLightsString,
                isNeedUpdateDataTemperatureString = isNeedUpdateDataTemperatureString
            )
            mainHandler.postDelayed(this, 1000)
        }
    })
}
