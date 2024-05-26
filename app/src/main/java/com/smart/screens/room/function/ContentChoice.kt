package com.smart.screens.room.function

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.smart.screens.room.pages.Humidity
import com.smart.screens.room.pages.Lights
import com.smart.screens.room.pages.Temperature
import androidx.compose.runtime.MutableState

@Composable
fun ContentChoice (
    route: MutableState<FunctionRoute>,
    functionItems: Array<FunctionItem>
) {
    for (navigationItem in functionItems) {
        if (navigationItem.route == route.value) {
            print(navigationItem.route)
            print(route.value)
            /*if (route.value === FunctionRoute.TEMPERATURE) {
                Temperature()
                break
            } else if (route.value === FunctionRoute.LIGHTS) {
                Lights()
                break
            } else if (route.value === FunctionRoute.HUMIDITY) {
                Humidity()
                break
            }*/
            when(route.value) {
                FunctionItem.Temperature.route -> Temperature()
                FunctionItem.Lights.route -> Lights()
                FunctionItem.Humidity.route -> Humidity()
                else -> {
                    print("x is neither 1 nor 2")
                }
            }
            break
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    val route = remember{
        mutableStateOf(FunctionItem.Lights.route)
    }
    val functionItems = arrayOf(
        FunctionItem.Temperature,
        FunctionItem.Lights,
        FunctionItem.Humidity
    )
    ContentChoice(
        route = route,
        functionItems = functionItems
    )
}