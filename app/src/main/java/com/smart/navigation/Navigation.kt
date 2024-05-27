package com.smart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smart.screens.SettingScreen
import com.smart.screens.Signaling
import com.smart.screens.home.HomeScreen
import com.smart.screens.room.pages.Humidity
import com.smart.screens.room.pages.Lights
import com.smart.screens.room.pages.Temperature

@Composable
fun Navigation(
    navController: NavHostController,
    titleTopBar: MutableState<String>,
    serverIp: MutableState<String>,
    serverPort: MutableState<String>
) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                navController = navController,
                titleTopBar = titleTopBar
            )
        }
        composable(NavigationItem.Signaling.route) {
            Signaling()
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
                functionItems = functionItems
            )
        }
        composable(NavigationItem.Lights.route) {
            Lights(
                navController = navController,
                functionItems = functionItems
            )
        }
        composable(NavigationItem.Humidity.route) {
            Humidity(
                navController = navController,
                functionItems = functionItems
            )
        }
    }
}
