package com.smart.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.smart.R
import com.smart.navigation.NavigationItem
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    titleTopBar: MutableState<String>,
    dataSelectedRoom: MutableState<RoomData>,
    serverIp: MutableState<String>,
    serverPort: MutableState<String>,
    dataRoomsString: MutableState<String>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    if (dataRoomsString.value.isEmpty()) {
        loadRoomData(
            serverIp = serverIp.value,
            serverPort = serverPort.value,
            dataRoomsString = dataRoomsString
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        val rows = 2
        FlowRow(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = rows,
        ) {
            if (dataRoomsString.value.isEmpty()) {
                Text(
                    text = "Данные не получены от сервера",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                )
                return
            }
            val dataRooms = Json.decodeFromString<Array<RoomData>>(dataRoomsString.value)
            for (roomData in dataRooms) {
                RoomCard(
                    navController = navController,
                    titleTopBar = titleTopBar,
                    roomData = roomData,
                    dataSelectedRoom = dataSelectedRoom
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val titleTopBar = remember{
        mutableStateOf(NavigationItem.Home.title)
    }
   val dataSelectedRoom = remember {
       mutableStateOf(
           RoomData(
               0,
               NavigationItem.Home.title,
               RoomIcon.LivingRoom.nameIcon
           )
       )
   }
    val serverIp = rememberSaveable { mutableStateOf("127.0.0.1") }
    val serverPort = rememberSaveable { mutableStateOf("8080") }
    val dataRoomsString = remember { mutableStateOf("[{\"id\":0,\"title\":\"Гостиная\",\"nameIcon\":\"living_room\"},{\"id\":1,\"title\":\"Спальня\",\"nameIcon\":\"bedroom\"},{\"id\":2,\"title\":\"Кухня\",\"nameIcon\":\"kitchen\"},{\"id\":3,\"title\":\"Ванная\",\"nameIcon\":\"bathroom\"},{\"id\":4,\"title\":\"Студия\",\"nameIcon\":\"studio\"}]") }
    HomeScreen(
        navController = navController,
        titleTopBar = titleTopBar,
        dataSelectedRoom = dataSelectedRoom,
        serverIp = serverIp,
        serverPort = serverPort,
        dataRoomsString = dataRoomsString
    )
}

fun loadRoomData(serverIp: String, serverPort: String, dataRoomsString: MutableState<String>) {
    if (serverIp.isEmpty() || serverPort.isEmpty()) {
        return
    }
    val tag = "MainActivity"
    val client = OkHttpClient()
    val url = "http://${serverIp}:${serverPort}/room-data"
    val request = Request.Builder()
        .url(url)
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) {
            response.use {
                val responseBody = response.body?.string() ?: ""
                if (responseBody.isEmpty()) {
                    return
                }
                dataRoomsString.value = responseBody
            }
        }
    })
}
