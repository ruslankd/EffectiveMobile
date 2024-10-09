package ru.kabirov.effectivemobile.main

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.ui.theme.Shadows

@SuppressLint("RestrictedApi")
@Composable
fun TabView(navController: NavHostController) {
    val topLevelRoutes = listOf(
        TopLevelRoute(
            name = stringResource(R.string.search_label),
            route = Search,
            selectedIconId = R.drawable.search,
            unselectedIconId = R.drawable.search
        ),
        TopLevelRoute(
            name = stringResource(R.string.favorites_label),
            route = Favorites,
            selectedIconId = R.drawable.heart,
            unselectedIconId = R.drawable.heart
        ),
        TopLevelRoute(
            name = stringResource(R.string.responses_label),
            route = Responses,
            selectedIconId = R.drawable.responses,
            unselectedIconId = R.drawable.responses
        ),
        TopLevelRoute(
            name = stringResource(R.string.messages_label),
            route = Messages,
            selectedIconId = R.drawable.messages,
            unselectedIconId = R.drawable.messages
        ),
        TopLevelRoute(
            name = stringResource(R.string.profile_label),
            route = Profile,
            selectedIconId = R.drawable.profile,
            unselectedIconId = R.drawable.profile
        ),
    )

    NavigationBar(containerColor = Shadows) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        topLevelRoutes.forEach { topLevelRoute ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = topLevelRoute.selectedIconId),
                        contentDescription = null
                    )
                },
                label = { Text(topLevelRoute.name) },
                selected = currentDestination?.hierarchy?.any { it.route == topLevelRoute.name } == true,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}