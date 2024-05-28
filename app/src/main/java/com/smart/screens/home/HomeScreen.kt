package com.smart.screens.home

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.smart.httpClient
import com.smart.navigation.NavigationItem
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.launch
import java.util.concurrent.TimeoutException

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    titleTopBar: MutableState<String>,
    dataSelectedRoom: MutableState<RoomData>,
    serverIp: MutableState<String>,
    serverPort: MutableState<String>
) {
    val coroutineScope = rememberCoroutineScope()
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)

    var dataRooms: Array<RoomData> = emptyArray()
    val isLoaded = rememberSaveable { mutableStateOf(false) }
    coroutineScope.launch {
        dataRooms = loadRoomData(
            serverIp = serverIp.value,
            serverPort = serverPort.value
        )
        isLoaded.value = true
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
            if (dataRooms.isEmpty()) {
                Text(
                    text = "Данные не получены от сервера",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                )
                return
            }
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
    HomeScreen(
        navController = navController,
        titleTopBar = titleTopBar,
        dataSelectedRoom = dataSelectedRoom,
        serverIp = serverIp,
        serverPort = serverPort
    )
}

suspend fun loadRoomData(serverIp: String, serverPort: String): Array<RoomData> {
    val tag = "MainActivity"
    try {
        val r = "http://${serverIp}:${serverPort}/room-data"
        val roomDataJson = httpClient.get<Array<RoomData>> {
            url("http://${serverIp}:${serverPort}/room-data")
        }
        httpClient.close()
        return roomDataJson
    } catch (e: ClientRequestException) {
        Log.d(tag, "ClientRequestException ${e.message}")
    } catch (e: ServerResponseException) {
        Log.d(tag, "ServerResponseException ${e.message}")
    } catch (e: TimeoutException) {
        Log.d(tag, "TimeoutException ${e.message}")
    } catch (e: Exception) {
        Log.d(tag, "Exception ${e.message}")
    } finally {
        httpClient.close()
    }
    return emptyArray()
    /*val json = """
        [
          {"id": 0, "title": "Гостинная", "nameIcon": "living_room"},
          {"id": 1, "title": "Спальня", "nameIcon": "bedroom"},
          {"id": 2, "title": "Кухня", "nameIcon": "kitchen"},
          {"id": 3, "title": "Ванная", "nameIcon": "bathroom"},
          {"id": 4, "title": "Студия", "nameIcon": "studio"}
        ] 
    """.trimIndent()
    return Json.decodeFromString<Array<RoomData>>(json)*/
}
