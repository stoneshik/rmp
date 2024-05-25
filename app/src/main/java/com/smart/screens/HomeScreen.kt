package com.smart.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smart.R
import com.smart.RoomCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen() {
    val backgroundColor: Color = colorResource(id = R.color.backgroundColor)
    val textColor: Color = colorResource(id = R.color.textColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.Center)
            .verticalScroll(rememberScrollState())
    ) {
        val rows = 2
        val columns = 3
        FlowRow(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = rows,
        ) {
            repeat(rows * columns) {
                RoomCard()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
