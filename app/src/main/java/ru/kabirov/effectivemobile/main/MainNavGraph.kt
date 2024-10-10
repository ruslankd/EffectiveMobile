package ru.kabirov.effectivemobile.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier, onNavigateToVacancyDetail: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = Search) {
            composable<Search> {
                SearchScreen(onNavigateToVacancyDetail)
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