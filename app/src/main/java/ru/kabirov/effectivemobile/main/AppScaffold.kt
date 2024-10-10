package ru.kabirov.effectivemobile.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun AppScaffold(navBarController: NavHostController, onNavigateToVacancyDetail: (String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        TabView(navController = navBarController)
    }) { innerPadding ->
        MainNavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navBarController,
            onNavigateToVacancyDetail = onNavigateToVacancyDetail
        )
    }
}