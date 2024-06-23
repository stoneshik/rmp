package com.smart.screens.room.function

import com.smart.navigation.NavigationItem

fun filterFunctionItems(
    functionItems: Array<NavigationItem>,
    enableFunctions: Array<String>
) : List<NavigationItem> {
    return functionItems.filter { it.route in enableFunctions }
}
