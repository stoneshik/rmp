package com.smart.client

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.MutableState

class DataLoader {
    private val DATA_ROOM_MAX_SECONDS: Int = 10
    private var dataRoomLivingSeconds: Int = 0

    fun updateDataStringsIfLifetimeExceed(
        serverIp: MutableState<String>,
        serverPort: MutableState<String>,
        dataRoomsString: MutableState<String>
    ) {
        dataRoomLivingSeconds++
        if (dataRoomLivingSeconds > DATA_ROOM_MAX_SECONDS) {
            dataRoomLivingSeconds = 0
            loadDataFromServer(
                serverIp = serverIp.value,
                serverPort = serverPort.value,
                dataString = dataRoomsString
            )
        }
    }
}


fun scheduleDataLoading(
    serverIp: MutableState<String>,
    serverPort: MutableState<String>,
    dataRoomsString: MutableState<String>
) {
    val dataLoader = DataLoader()
    val mainHandler = Handler(Looper.getMainLooper())
    mainHandler.post(object : Runnable {
        override fun run() {
            dataLoader.updateDataStringsIfLifetimeExceed(
                serverIp = serverIp,
                serverPort = serverPort,
                dataRoomsString = dataRoomsString
            )
            mainHandler.postDelayed(this, 1000)
        }
    })
}
