package ru.kabirov.effectivemobile.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.kabirov.effectivemobile.ui.theme.EffectiveMobileTheme
import ru.kabirov.effectivemobile.ui.theme.Shadows

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()

            EffectiveMobileTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Shadows, bottomBar = {
                    TabView(navController = navController)
                }) { innerPadding ->
                    MainNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }


}