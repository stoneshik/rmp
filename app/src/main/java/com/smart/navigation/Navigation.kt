package com.smart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smart.screens.HomeScreen
import com.smart.screens.SettingScreen
import com.smart.screens.Signaling

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Signaling.route) {
            Signaling()
        }
        composable(NavigationItem.Settings.route) {
            SettingScreen()
        }
    }
}