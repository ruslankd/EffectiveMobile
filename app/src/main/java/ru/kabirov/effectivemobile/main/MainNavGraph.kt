package ru.kabirov.effectivemobile.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.kabirov.effectivemobile.favorites.FavoritesScreen
import ru.kabirov.effectivemobile.other_screens.MessagesScreen
import ru.kabirov.effectivemobile.other_screens.ProfileScreen
import ru.kabirov.effectivemobile.other_screens.ResponsesScreen
import ru.kabirov.effectivemobile.search.SearchScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        NavHost(navController = navController, startDestination = Search) {
            composable<Search> {
                SearchScreen()
            }

            composable<Favorites> {
                FavoritesScreen()
            }

            composable<Responses> {
                ResponsesScreen()
            }

            composable<Messages> {
                MessagesScreen()
            }

            composable<Profile> {
                ProfileScreen()
            }
        }

    }
}