package com.smart.client

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.MutableState

class DataLoadedClearer {
    private val DATA_ROOM_MAX_SECONDS: Int = 10
    private var dataRoomLivingSeconds: Int = 0

    /**
     * Фукнцию нужно вызывать каждую секунду в отдельном потоке
     * она проверяет нужно ли обнулить значение для того чтобы элемент перерисовался
     * и при его перерисовке подгрузились новые данные с сервера
     */
    fun clearDataStringsIfLifetimeExceed(
        dataRoomsString: MutableState<String>
    ) {
        dataRoomLivingSeconds++
        if (dataRoomLivingSeconds > DATA_ROOM_MAX_SECONDS) {
            dataRoomLivingSeconds = 0
            updateDataString(dataRoomsString)
        }
    }

    private fun updateDataString(dataString: MutableState<String>) {
        if (dataString.value.isEmpty()) {
            dataString.value = "0"
        }
        dataString.value = ""
    }
}


fun planningClearDataStrings(
    dataRoomsString: MutableState<String>
) {
    val dataLoadedClearer = DataLoadedClearer()
    val mainHandler = Handler(Looper.getMainLooper())
    mainHandler.post(object : Runnable {
        override fun run() {
            dataLoadedClearer.clearDataStringsIfLifetimeExceed(
                dataRoomsString = dataRoomsString
            )
            mainHandler.postDelayed(this, 1000)
        }
    })
}
