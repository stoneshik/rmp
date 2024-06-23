package com.smart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.smart.client.scheduleDataLoading
import com.smart.navigation.BottomNavigationBar
import com.smart.navigation.Navigation
import com.smart.navigation.NavigationItem
import com.smart.screens.home.RoomData
import com.smart.screens.home.RoomIcon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val backgroundColor = colorResource(id = R.color.backgroundColor)
    val navController = rememberNavController()
    val titleTopBar = remember { mutableStateOf(NavigationItem.Home.title) }
    val serverIp = rememberSaveable { mutableStateOf("192.168.0.192") }
    val serverPort = rememberSaveable { mutableStateOf("8080") }
    val signalingIsWork = remember { mutableStateOf(false) }
    val signalingState = remember { mutableStateOf(true) }
    val dataSelectedRoom = remember {
        mutableStateOf(
            RoomData(
                0,
                NavigationItem.Home.title,
                RoomIcon.LivingRoom.nameIcon
            )
        )
    }
    val dataRoomsString = remember { mutableStateOf("") }

    scheduleDataLoading(
        serverIp = serverIp,
        serverPort = serverPort,
        dataRoomsString = dataRoomsString
    )

    Scaffold(
        topBar = { TopBar(titleTopBar) },
        bottomBar = {
            BottomNavigationBar(
                titleTopBar = titleTopBar,
                navController = navController
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    navController = navController,
                    titleTopBar = titleTopBar,
                    serverIp = serverIp,
                    serverPort = serverPort,
                    signalingIsWork = signalingIsWork,
                    signalingState = signalingState,
                    dataSelectedRoom = dataSelectedRoom,
                    dataRoomsString = dataRoomsString
                )
            }
        },
        containerColor = backgroundColor // Set background color to avoid the white flashing when you switch between screens
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
