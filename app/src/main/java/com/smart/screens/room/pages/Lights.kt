package com.smart.screens.room.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.smart.R
import com.smart.navigation.NavigationItem
import com.smart.screens.room.function.FunctionNavigationBar
import kotlin.math.roundToInt

@Composable
fun Lights(
    navController: NavController,
    functionItems: Array<NavigationItem>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    val backgroundSelectElementColor: Color = colorResource(
        id = R.color.backgroundSelectElementColor
    )
    val selectElementTextColor: Color = colorResource(id = R.color.selectElementTextColor)
    val valueLight = remember { mutableFloatStateOf(0f) }

    val pageRoute = NavigationItem.Lights.route
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        FunctionNavigationBar(
            navController = navController,
            pageRoute = pageRoute,
            functionItems = functionItems
        )
        Text(
            text = "Освещенность сейчас: 100%",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 64.dp)
        )
        Text(
            text = "Текущее значение: ${valueLight.floatValue.roundToInt().toFloat()}%",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        Slider(
            value = valueLight.floatValue,
            valueRange = 0f..100f,
            steps = 19,
            onValueChange = { valueLight.floatValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .padding(horizontal = 12.dp)
        )
        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 12.dp),
            colors = ButtonColors(
                contentColor = selectElementTextColor,
                containerColor = backgroundSelectElementColor,
                disabledContentColor = selectElementTextColor,
                disabledContainerColor = backgroundSelectElementColor
            )
        ) {
            Text(
                text = "Установить значение",
                color = selectElementTextColor,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightsPreview() {
    val navController = rememberNavController()
    val functionItems = arrayOf(
        NavigationItem.Temperature,
        NavigationItem.Lights,
        NavigationItem.Humidity
    )
    Lights(
        navController = navController,
        functionItems = functionItems
    )
}
