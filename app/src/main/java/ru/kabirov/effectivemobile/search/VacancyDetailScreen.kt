package ru.kabirov.effectivemobile.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.ui.theme.Title2
import ru.kabirov.effectivemobile.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyDetailScreen(title: String, onBackBtnClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = White
                ),
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = onBackBtnClick
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.left_arrow),
                            contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title, style = Title2, color = White)
        }
    }
}