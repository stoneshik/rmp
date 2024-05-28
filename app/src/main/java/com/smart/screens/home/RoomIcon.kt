package com.smart.screens.home

import com.smart.R

sealed class RoomIcon(var nameIcon: String, var icon: Int) {
    data object LivingRoom : RoomIcon (
        "living_room", R.drawable.ic_chair
    )
    data object Bedroom : RoomIcon(
        "bedroom", R.drawable.ic_bed
    )
    data object Kitchen : RoomIcon(
        "kitchen", R.drawable.ic_fridge
    )
    data object Bathroom : RoomIcon(
        "bathroom", R.drawable.ic_shower
    )
    data object Studio : RoomIcon(
        "studio", R.drawable.ic_table_lamp
    )
}

fun getRoomIconByNameIcon(roomIcons: Array<RoomIcon>, nameIcon: String) : Int {
    for (roomIcon in roomIcons) {
        if (roomIcon.nameIcon.equals(nameIcon)) {
            return roomIcon.icon
        }
    }
    return RoomIcon.LivingRoom.icon
}
