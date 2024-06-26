package com.smart.screens.home

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
import com.smart.client.getDataFromServer
import com.smart.navigation.NavigationItem
import kotlinx.serialization.json.Json


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    titleTopBar: MutableState<String>,
    serverIp: MutableState<String>,
    serverPort: MutableState<String>,
    dataSelectedRoom: MutableState<RoomData>,
    dataRoomsString: MutableState<String>,
    isNeedUpdateDataRoomsString: MutableState<Boolean>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    if (isNeedUpdateDataRoomsString.value) {
        getDataFromServer(
            serverIp = serverIp.value,
            serverPort = serverPort.value,
            dataString = dataRoomsString,
            endpointName = "room-data"
        )
        isNeedUpdateDataRoomsString.value = false
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
                    text = "Нет соединения с сервером",
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
    val serverIp = rememberSaveable { mutableStateOf("") }
    val serverPort = rememberSaveable { mutableStateOf("") }
    val dataSelectedRoom = remember {
        mutableStateOf(
            RoomData(
                0,
                NavigationItem.Home.title,
                RoomIcon.LivingRoom.nameIcon
            )
        )
    }
    val dataRoomsString = remember {
        mutableStateOf(
            "[{\"id\":0,\"title\":\"Гостиная\",\"nameIcon\":\"living_room\"},{\"id\":1,\"title\":\"Спальня\",\"nameIcon\":\"bedroom\"},{\"id\":2,\"title\":\"Кухня\",\"nameIcon\":\"kitchen\"},{\"id\":3,\"title\":\"Ванная\",\"nameIcon\":\"bathroom\"},{\"id\":4,\"title\":\"Студия\",\"nameIcon\":\"studio\"}]"
        )
    }
    val isNeedUpdateDataRoomsString = remember { mutableStateOf(false) }
    HomeScreen(
        navController = navController,
        titleTopBar = titleTopBar,
        serverIp =  serverIp,
        serverPort =  serverPort,
        dataSelectedRoom = dataSelectedRoom,
        dataRoomsString = dataRoomsString,
        isNeedUpdateDataRoomsString = isNeedUpdateDataRoomsString
    )
}
