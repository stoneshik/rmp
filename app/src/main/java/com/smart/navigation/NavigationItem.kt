package com.smart.navigation

import com.smart.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    data object Graphs : NavigationItem("graphs", R.drawable.ic_music, "Graphs")
    data object Settings : NavigationItem("settings", R.drawable.ic_movie, "Settings")
}