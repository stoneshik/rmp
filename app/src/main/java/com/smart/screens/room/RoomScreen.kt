package com.smart.screens.room

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.smart.R
import com.smart.screens.room.function.ContentChoice
import com.smart.screens.room.function.FunctionItem
import com.smart.screens.room.function.FunctionNavigationBar


@Composable
fun RoomScreen() {
    val backgroundColor = colorResource(id = R.color.backgroundColor)
    val route = remember{
        mutableStateOf(FunctionItem.Lights.route)
    }
    val functionItems = arrayOf(
        FunctionItem.Temperature,
        FunctionItem.Lights,
        FunctionItem.Humidity
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        FunctionNavigationBar(
            route = route,
            functionItems = functionItems
        )
        ContentChoice(
            route = route,
            functionItems = functionItems
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoomScreenPreview() {
    RoomScreen()
}
