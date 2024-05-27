package com.smart.screens.room.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.smart.R
import com.smart.navigation.NavigationItem
import com.smart.screens.room.function.FunctionNavigationBar

@Composable
fun Humidity(
    navController: NavController,
    functionItems: Array<NavigationItem>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    val pageRoute = NavigationItem.Humidity.route
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.TopCenter)
            .horizontalScroll(rememberScrollState())
    ) {
        FunctionNavigationBar(
            navController = navController,
            pageRoute = pageRoute,
            functionItems = functionItems
        )
        Text(
            text = "hum",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HumidityPreview() {
    val navController = rememberNavController()
    val functionItems = arrayOf(
        NavigationItem.Temperature,
        NavigationItem.Lights,
        NavigationItem.Humidity
    )
    Humidity(
        navController = navController,
        functionItems = functionItems
    )
}
