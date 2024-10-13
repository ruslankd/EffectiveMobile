package ru.kabirov.effectivemobile.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.search.SearchViewModel
import ru.kabirov.effectivemobile.ui.theme.Blue
import ru.kabirov.effectivemobile.ui.theme.Grey1
import ru.kabirov.effectivemobile.ui.theme.Grey4
import ru.kabirov.effectivemobile.ui.theme.Number
import ru.kabirov.effectivemobile.ui.theme.Red
import ru.kabirov.effectivemobile.ui.theme.TabText
import ru.kabirov.effectivemobile.ui.theme.White

@SuppressLint("RestrictedApi")
@Composable
fun TabView(
    navController: NavHostController,
    vm: SearchViewModel = hiltViewModel()
) {
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

    NavigationBar(modifier = Modifier.drawWithContent {
        drawContent()
        drawLine(
            color = Grey1,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx(),
        )
    }, containerColor = MaterialTheme.colorScheme.background, contentColor = Grey4) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        topLevelRoutes.forEach { topLevelRoute ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Blue,
                    selectedTextColor = Blue,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Grey4,
                    unselectedTextColor = Grey4
                ),
                icon = {
                    if (topLevelRoute.route is Favorites) {
                        BadgedBox(badge = {
                            if (vm.favoritesVacancies.collectAsStateWithLifecycle().value.isNotEmpty()) {
                                Badge(
                                    containerColor = Red,
                                    contentColor = White
                                ) {
                                    Text(
                                        text = vm.favoritesVacancies.collectAsStateWithLifecycle().value.size.toString(),
                                        style = Number
                                    )
                                }
                            }
                        }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = topLevelRoute.selectedIconId),
                                contentDescription = null
                            )
                        }
                    } else {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = topLevelRoute.selectedIconId),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(text = topLevelRoute.name, style = TabText) },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
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
//    }
}