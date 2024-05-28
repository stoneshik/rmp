package com.smart.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.smart.R
import com.smart.navigation.NavigationItem
import com.smart.navigation.changePage

@Composable
fun RoomCard(
    navController: NavController,
    titleTopBar: MutableState<String>,
    dataSelectedRoom: MutableState<RoomData>,
    roomData: RoomData
) {
    val roomIcons = arrayOf(
        RoomIcon.LivingRoom,
        RoomIcon.Bedroom,
        RoomIcon.Kitchen,
        RoomIcon.Bathroom,
        RoomIcon.Studio
    )
    val backgroundElementColor: Color = colorResource(id = R.color.backgroundElementColor)
    val pageRoute: String = NavigationItem.Temperature.route
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundElementColor,
        ),
        modifier = Modifier.clickable(
            onClick = {
                changePage(
                    navController = navController,
                    pageRoute = pageRoute,
                    titleTopBar = titleTopBar,
                    pageTitle = roomData.title
                )
                dataSelectedRoom.value = roomData
            }
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .width(150.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(4.dp)),
        ) {
            Image(
                painter = painterResource(
                    getRoomIconByNameIcon(
                        roomIcons = roomIcons,
                        nameIcon = roomData.nameIcon
                    )
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = roomData.title,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RoomCardPreview() {
    val navController = rememberNavController()
    val titleTopBar = remember{
        mutableStateOf(NavigationItem.Home.title)
    }
    val roomData = RoomData(
        0,
        NavigationItem.Home.title,
        RoomIcon.LivingRoom.nameIcon
    )
    val dataSelectedRoom = remember {
        mutableStateOf(
            RoomData(
                0,
                NavigationItem.Home.title,
                RoomIcon.LivingRoom.nameIcon
            )
        )
    }
    RoomCard(
        navController = navController,
        titleTopBar = titleTopBar,
        dataSelectedRoom = dataSelectedRoom,
        roomData = roomData
    )
}
