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
        isNeedUpdateDataRoomsString: MutableState<Boolean>
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
        if (dataRoomLivingSeconds > DATA_ROOM_MAX_SECONDS) {
            dataRoomLivingSeconds = 0
            updateFlag(isNeedUpdateDataRoomsString)
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
    isNeedUpdateDataRoomsString: MutableState<Boolean>
) {
    val dataLoader = DataLoader()
    val mainHandler = Handler(Looper.getMainLooper())
    mainHandler.post(object : Runnable {
        override fun run() {
            dataLoader.updateDataStringsIfLifetimeExceed(
                serverIp = serverIp,
                serverPort = serverPort,
                isNeedUpdateDataRoomsString = isNeedUpdateDataRoomsString
            )
            mainHandler.postDelayed(this, 1000)
        }
    })
}
