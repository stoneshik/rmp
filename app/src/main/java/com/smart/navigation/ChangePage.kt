package com.smart.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController

fun changePage(
    navController: NavController,
    pageRoute: String,
    titleTopBar: MutableState<String>,
    pageTitle: String
) {
    navController.navigate(pageRoute) {
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
        titleTopBar.value = pageTitle
    }
}
