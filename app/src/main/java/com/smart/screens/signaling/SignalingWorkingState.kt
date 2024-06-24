package com.smart.screens.signaling

enum class SignalingWorkingState(val state: String, val description: String) {
    WORK("work", "Работает"),
    SWITCH_OFF("switch_off", "Выключена"),
    TURNS_ON("turns_on", "Включается"),
    TURNS_OFF("turns_off", "Выключается")
}
