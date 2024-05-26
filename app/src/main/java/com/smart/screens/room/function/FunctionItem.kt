package com.smart.screens.room.function

import com.smart.R

sealed class FunctionItem(var route: FunctionRoute, var icon: Int, var title: String) {
    data object Temperature : FunctionItem(
        FunctionRoute.TEMPERATURE, R.drawable.ic_home, "Температура"
    )
    data object Lights : FunctionItem(
        FunctionRoute.LIGHTS, R.drawable.ic_signaling, "Свет"
    )
    data object Humidity : FunctionItem(
        FunctionRoute.HUMIDITY, R.drawable.ic_settings, "Влажность"
    )
}
