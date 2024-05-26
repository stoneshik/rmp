package com.smart.navigation

import com.smart.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Home : NavigationItem(
        "home", R.drawable.ic_home, "Главная"
    )
    data object Signaling : NavigationItem(
        "signaling", R.drawable.ic_signaling, "Сигнализация"
    )
    data object Settings : NavigationItem(
        "settings", R.drawable.ic_settings, "Настройки"
    )
    data object Room : NavigationItem(
        "room", R.drawable.ic_bed, "Комната"
    )
}
