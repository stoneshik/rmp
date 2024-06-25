package com.smart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.smart.client.scheduleDataLoading
import com.smart.navigation.BottomNavigationBar
import com.smart.navigation.Navigation
import com.smart.navigation.NavigationItem
import com.smart.screens.home.RoomData
import com.smart.screens.home.RoomIcon
import com.smart.screens.signaling.SignalingData
import com.smart.screens.signaling.SignalingWorkingState
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val backgroundColor = colorResource(id = R.color.backgroundColor)
    val navController = rememberNavController()
    val titleTopBar = remember { mutableStateOf(NavigationItem.Home.title) }
    val serverIp = rememberSaveable { mutableStateOf("192.168.0.192") }
    val serverPort = rememberSaveable { mutableStateOf("8080") }
    val dataSignalingString = remember { mutableStateOf("") }
    val dataSelectedRoom = remember {
        mutableStateOf(
            RoomData(
                0,
                NavigationItem.Home.title,
                RoomIcon.LivingRoom.nameIcon
            )
        )
    }
    val dataRoomsString = remember { mutableStateOf("") }
    val isNeedUpdateDataRoomsString = remember { mutableStateOf(true) }

    val dataHumidityString = remember { mutableStateOf("") }
    val isNeedUpdateDataHumidityString = remember { mutableStateOf(true) }
    val dataLightsString = remember { mutableStateOf("") }
    val isNeedUpdateDataLightsString = remember { mutableStateOf(true) }
    val dataTemperatureString = remember { mutableStateOf("") }
    val isNeedUpdateDataTemperatureString = remember { mutableStateOf(true) }

    scheduleDataLoading(
        serverIp = serverIp,
        serverPort = serverPort,
        dataSignalingString = dataSignalingString,
        isNeedUpdateDataRoomsString = isNeedUpdateDataRoomsString,
        isNeedUpdateDataHumidityString = isNeedUpdateDataHumidityString,
        isNeedUpdateDataLightsString = isNeedUpdateDataLightsString,
        isNeedUpdateDataTemperatureString = isNeedUpdateDataTemperatureString
    )

    Scaffold(
        topBar = { TopBar(titleTopBar) },
        bottomBar = {
            BottomNavigationBar(
                titleTopBar = titleTopBar,
                navController = navController
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    navController = navController,
                    titleTopBar = titleTopBar,
                    serverIp = serverIp,
                    serverPort = serverPort,
                    dataSignalingString = dataSignalingString,
                    dataSelectedRoom = dataSelectedRoom,
                    dataRoomsString = dataRoomsString,
                    isNeedUpdateDataRoomsString = isNeedUpdateDataRoomsString,

                    dataHumidityString = dataHumidityString,
                    isNeedUpdateDataHumidityString = isNeedUpdateDataHumidityString,
                    dataLightsString = dataLightsString,
                    isNeedUpdateDataLightsString = isNeedUpdateDataLightsString,
                    dataTemperatureString = dataTemperatureString,
                    isNeedUpdateDataTemperatureString = isNeedUpdateDataTemperatureString
                )
                if (!checkAlarm(dataSignalingString)) {
                    Snackbar {
                        Text(
                            "Тревога!!! Сигнализация сработала",
                            fontSize = 22.sp,
                            color = colorResource(id = R.color.selectElementTextColor)
                        )
                    }
                }
            }
        },
        containerColor = backgroundColor // Set background color to avoid the white flashing when you switch between screens
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

fun checkAlarm(dataSignalingString: MutableState<String>): Boolean {
    if (dataSignalingString.value.isEmpty()) {
        return true
    }
    val dataSignaling = Json.decodeFromString<SignalingData>(dataSignalingString.value)
    return dataSignaling.signalingState ||
            !dataSignaling.signalingWorkingState.equals(SignalingWorkingState.WORK.state)
}
