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
    data object Temperature : NavigationItem(
        "temperature_room", R.drawable.ic_thermostat, "Температура"
    )
    data object Lights : NavigationItem(
        "lights_room", R.drawable.ic_lamp, "Свет"
    )
    data object Humidity : NavigationItem(
        "humidity_room", R.drawable.ic_water_drop, "Влажность"
    )
}
