package com.smart.navigation

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.smart.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val pages = pages
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
                    navController.navigate(page.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    BottomNavigationBar(navController)
}
