package com.smart.screens.room.function

import com.smart.navigation.NavigationItem
import kotlinx.serialization.json.Json


fun loadAndFilterFunctionItems(functionItems: Array<NavigationItem>) : List<NavigationItem> {
    val loadedFunctionCardsEnable = loadFunctionCardsEnable()
    return filterFunctionItems(
        functionItems = functionItems,
        functionCardEnable = loadedFunctionCardsEnable
    )
}

private fun loadFunctionCardsEnable(): Array<String> {
    val json = """
        [
         "temperature_room",
         "lights_room",
         "humidity_room"
        ] 
    """.trimIndent()
    return Json.decodeFromString<Array<String>>(json)
}

private fun filterFunctionItems(
    functionItems: Array<NavigationItem>,
    functionCardEnable: Array<String>
) : List<NavigationItem> {
    return functionItems.filter { it.route in functionCardEnable }
}