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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.smart.client.getDataFromServer
import com.smart.navigation.NavigationItem
import com.smart.screens.home.RoomData
import com.smart.screens.home.RoomIcon
import com.smart.screens.room.FunctionLightsData
import com.smart.screens.room.function.FunctionNavigationBar
import com.smart.screens.room.function.filterFunctionItems
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

@Composable
fun Lights(
    navController: NavController,
    functionItems: Array<NavigationItem>,
    serverIp: MutableState<String>,
    serverPort: MutableState<String>,
    dataLightsString: MutableState<String>,
    isNeedUpdateDataLightsString: MutableState<Boolean>,
    dataSelectedRoom: MutableState<RoomData>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    val backgroundSelectElementColor: Color = colorResource(
        id = R.color.backgroundSelectElementColor
    )
    val selectElementTextColor: Color = colorResource(id = R.color.selectElementTextColor)
    val valueLight = remember { mutableFloatStateOf(0f) }
    val pageRoute = NavigationItem.Lights.route
    if (isNeedUpdateDataLightsString.value) {
        getDataFromServer(
            serverIp = serverIp.value,
            serverPort = serverPort.value,
            dataString = dataLightsString,
            endpointName = "lights-data/${dataSelectedRoom.value.id}"
        )
        isNeedUpdateDataLightsString.value = false
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        if (dataLightsString.value.isEmpty()) {
            Text(
                text = "Нет соединения с сервером",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            )
            return
        }
        val dataLights = Json.decodeFromString<FunctionLightsData>(dataLightsString.value)
        val filteredFunctionItems = filterFunctionItems(
            functionItems = functionItems,
            enableFunctions = dataLights.enableFunctions
        )
        if (filteredFunctionItems.isEmpty()) {
            Text(
                text = "У выбранной комнаты нет функций",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            )
            return
        }
        FunctionNavigationBar(
            navController = navController,
            pageRoute = pageRoute,
            functionItems = filteredFunctionItems
        )
        Text(
            text = "Освещенность сейчас: ${dataLights.lights}%",
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
    val serverIp = rememberSaveable { mutableStateOf("") }
    val serverPort = rememberSaveable { mutableStateOf("") }
    val dataLightsString = remember {
        mutableStateOf(
            "{\"idRoom\":1,\"enableFunctions\":[\"humidity_room\",\"lights_room\",\"temperature_room\"],\"lights\":90.0}"
        )
    }
    val isNeedUpdateDataLightsString = remember { mutableStateOf(false) }
    val dataSelectedRoom = remember {
        mutableStateOf(
            RoomData(
                0,
                NavigationItem.Home.title,
                RoomIcon.LivingRoom.nameIcon
            )
        )
    }
    Lights(
        navController = navController,
        functionItems = functionItems,
        serverIp = serverIp,
        serverPort = serverPort,
        dataLightsString = dataLightsString,
        isNeedUpdateDataLightsString = isNeedUpdateDataLightsString,
        dataSelectedRoom = dataSelectedRoom
    )
}
