package com.smart.screens.room.function

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smart.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FunctionNavigationBar(
    route: MutableState<FunctionRoute>,
    functionItems: Array<FunctionItem>
) {
    val backgroundElementColor: Color = colorResource(id = R.color.backgroundElementColor)
    val textColor: Color = colorResource(id = R.color.textColor)
    val backgroundSelectElementColor: Color = colorResource(id = R.color.backgroundSelectElementColor)
    val selectElementTextColor: Color = colorResource(id = R.color.selectElementTextColor)
    val rows = 3
    FlowRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = rows,
    ) {
        for (navigationItem in functionItems) {
            if (navigationItem.route == route.value) {
                FunctionCard(
                    route = route,
                    functionItem = navigationItem,
                    backgroundElementColor = backgroundSelectElementColor,
                    textColor = selectElementTextColor
                )
                continue
            }
            FunctionCard(
                route = route,
                functionItem = navigationItem,
                backgroundElementColor = backgroundElementColor,
                textColor = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FunctionNavigationBarPreview() {
    val route = remember{
        mutableStateOf(FunctionItem.Lights.route)
    }
    val functionItems = arrayOf(
        FunctionItem.Temperature,
        FunctionItem.Lights,
        FunctionItem.Humidity
    )
    FunctionNavigationBar(
        route = route,
        functionItems = functionItems
    )
}
