package com.smart.screens.room.function

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.smart.R
import com.smart.navigation.NavigationItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FunctionNavigationBar(
    navController: NavController,
    pageRoute: String,
    functionItems: Array<NavigationItem>
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
            if (navigationItem.route.equals(pageRoute)) {
                FunctionCard(
                    navController = navController,
                    functionItem = navigationItem,
                    backgroundElementColor = backgroundSelectElementColor,
                    textColor = selectElementTextColor
                )
            } else {
                FunctionCard(
                    navController = navController,
                    functionItem = navigationItem,
                    backgroundElementColor = backgroundElementColor,
                    textColor = textColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FunctionNavigationBarPreview() {
    val navController = rememberNavController()
    val pageRoute = NavigationItem.Humidity.route
    val functionItems = arrayOf(
        NavigationItem.Temperature,
        NavigationItem.Lights,
        NavigationItem.Humidity
    )
    FunctionNavigationBar(
        navController = navController,
        pageRoute = pageRoute,
        functionItems = functionItems
    )
}
