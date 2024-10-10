package ru.kabirov.effectivemobile.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import ru.kabirov.effectivemobile.search.VacancyDetailScreen
import ru.kabirov.effectivemobile.ui.theme.EffectiveMobileTheme
import ru.kabirov.effectivemobile.ui.theme.Shadows

fun Context.logd(message: String) {
    Log.d(this::class.java.simpleName, message)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navBarController: NavHostController
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle =
            SystemBarStyle.dark(
                Shadows.toArgb()
            ),
            navigationBarStyle =
            SystemBarStyle.dark(
                Shadows.toArgb()
            )
        )

        setContent {
            navBarController = rememberNavController()
            navController = rememberNavController()

            EffectiveMobileTheme {
                NavHost(navController = navController, startDestination = AppScaffold) {
                    composable<AppScaffold> {
                        AppScaffold(navBarController, onNavigateToVacancyDetail = { title ->
                            navController.navigate(VacancyDetail(title))
                        })
                    }
                    composable<VacancyDetail> { backStackEntry ->
                        val vacancyDetail: VacancyDetail = backStackEntry.toRoute()
                        VacancyDetailScreen(vacancyDetail.title, onBackBtnClick = {
                            navController.navigateUp()
                        })
                    }
                }
            }
        }
    }


}