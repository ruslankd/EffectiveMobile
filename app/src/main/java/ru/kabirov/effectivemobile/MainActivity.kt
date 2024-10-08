package ru.kabirov.effectivemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.ui.theme.EffectiveMobileTheme
import ru.kabirov.effectivemobile.ui.theme.Shadows

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EffectiveMobileTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Shadows) { innerPadding ->
                    Button(modifier = Modifier.padding(top = 16.dp), onClick = {}) {
                        Text("sad")
                    }
                }
            }
        }
    }
}