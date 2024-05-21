package com.smart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smart.screens.GraphScreen
import com.smart.screens.HomeScreen
import com.smart.screens.SettingScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Graphs.route) {
            GraphScreen()
        }
        composable(NavigationItem.Settings.route) {
            SettingScreen()
        }
    }
}