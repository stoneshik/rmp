package com.smart.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
fun HomeScreen(
    navController: NavController,
    titleTopBar: MutableState<String>
) {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    val valuesCards = listOf(
        listOf("Гостиная", RoomIcon.LivingRoom),
        listOf("Спальня", RoomIcon.Bedroom),
        listOf("Кухня", RoomIcon.Kitchen),
        listOf("Ванная", RoomIcon.Bathroom),
        listOf("Студия", RoomIcon.Studio),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        val rows = 2
        FlowRow(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = rows,
        ) {
            for (valueCard in valuesCards) {
                RoomCard(
                    navController = navController,
                    titleTopBar = titleTopBar,
                    titleText = valueCard[0].toString(),
                    roomIcon = valueCard[1] as RoomIcon
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val titleTopBar = remember{
        mutableStateOf(NavigationItem.Home.title)
    }
    HomeScreen(
        navController = navController,
        titleTopBar = titleTopBar
    )
}
