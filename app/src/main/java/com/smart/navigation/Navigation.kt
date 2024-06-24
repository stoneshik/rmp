package com.smart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smart.screens.SettingScreen
import com.smart.screens.Signaling
import com.smart.screens.home.HomeScreen
import com.smart.screens.home.RoomData
import com.smart.screens.room.pages.Humidity
import com.smart.screens.room.pages.Lights
import com.smart.screens.room.pages.Temperature

@Composable
fun Navigation(
    navController: NavHostController,
    titleTopBar: MutableState<String>,
    serverIp: MutableState<String>,
    serverPort: MutableState<String>,
    signalingIsWork: MutableState<Boolean>,
    signalingState: MutableState<Boolean>,
    dataSelectedRoom: MutableState<RoomData>,

    dataRoomsString: MutableState<String>,
    isNeedUpdateDataRoomsString: MutableState<Boolean>,

    dataHumidityString: MutableState<String>,
    isNeedUpdateDataHumidityString: MutableState<Boolean>,
    dataLightsString: MutableState<String>,
    isNeedUpdateDataLightsString: MutableState<Boolean>,
    dataTemperatureString: MutableState<String>,
    isNeedUpdateDataTemperatureString: MutableState<Boolean>
) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
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
        composable(NavigationItem.Signaling.route) {
            Signaling(
                signalingIsWork = signalingIsWork,
                signalingState = signalingState
            )
        }
        composable(NavigationItem.Settings.route) {
            SettingScreen(
                serverIp = serverIp,
                serverPort = serverPort
            )
        }
        val functionItems = arrayOf(
            NavigationItem.Temperature,
            NavigationItem.Lights,
            NavigationItem.Humidity
        )
        composable(NavigationItem.Temperature.route) {
            Temperature(
                navController = navController,
                functionItems = functionItems,
                serverIp = serverIp,
                serverPort = serverPort,
                dataTemperatureString = dataTemperatureString,
                isNeedUpdateDataTemperatureString = isNeedUpdateDataTemperatureString,
                dataSelectedRoom = dataSelectedRoom
            )
        }
        composable(NavigationItem.Lights.route) {
            Lights(
                navController = navController,
                functionItems = functionItems,
                serverIp = serverIp,
                serverPort = serverPort,
                dataLightsString = dataLightsString,
                isNeedUpdateDataLightsString = isNeedUpdateDataLightsString,
                dataSelectedRoom = dataSelectedRoom
            )
        }
        composable(NavigationItem.Humidity.route) {
            Humidity(
                navController = navController,
                functionItems = functionItems,
                serverIp = serverIp,
                serverPort = serverPort,
                dataHumidityString = dataHumidityString,
                isNeedUpdateDataHumidityString = isNeedUpdateDataHumidityString,
                dataSelectedRoom = dataSelectedRoom
            )
        }
    }
}
