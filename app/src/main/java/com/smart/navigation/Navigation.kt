package com.smart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smart.screens.SettingScreen
import com.smart.screens.Signaling
import com.smart.screens.home.HomeScreen
import com.smart.screens.room.RoomScreen

@Composable
fun Navigation(
    navController: NavHostController,
    titleTopBar: MutableState<String>
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
            SettingScreen()
        }
        composable(NavigationItem.Room.route) {
            RoomScreen()
        }
    }
}