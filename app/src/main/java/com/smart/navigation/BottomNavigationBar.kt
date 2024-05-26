package com.smart.navigation

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.smart.R

@Composable
fun BottomNavigationBar(navController: NavController, titleTopBar: MutableState<String>) {
    val pages = arrayOf(
        NavigationItem.Home,
        NavigationItem.Signaling,
        NavigationItem.Settings
    )
    val backgroundColor = colorResource(id = R.color.backgroundColor)
    val textColor = colorResource(id = R.color.textColor)
    val selectedColor = colorResource(id = R.color.selectedColor)
    BottomNavigation(
        backgroundColor = backgroundColor,
        contentColor = textColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        pages.forEach { page ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = page.icon), contentDescription = page.title) },
                label = { Text(text = page.title) },
                selectedContentColor = selectedColor,
                unselectedContentColor = textColor,
                alwaysShowLabel = true,
                selected = currentRoute == page.route,
                onClick = {
                    changePage(
                        navController = navController,
                        pageRoute = page.route,
                        titleTopBar = titleTopBar,
                        pageTitle = page.title
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    val titleTopBar = remember{
        mutableStateOf(NavigationItem.Home.title)
    }
    BottomNavigationBar(navController, titleTopBar)
}