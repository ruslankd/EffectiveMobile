package ru.kabirov.effectivemobile.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.ui.theme.Blue
import ru.kabirov.effectivemobile.ui.theme.DarkBlue
import ru.kabirov.effectivemobile.ui.theme.DarkGreen
import ru.kabirov.effectivemobile.ui.theme.Green
import ru.kabirov.effectivemobile.ui.theme.Grey4
import ru.kabirov.effectivemobile.ui.theme.White

@Composable
fun BlueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enable: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = White,
            disabledContainerColor = DarkBlue,
            disabledContentColor = Grey4
        ),
        onClick = { if (enable) onClick.invoke() else null },
        content = content
    )
}

@Composable
fun GreenButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enable: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = White,
            disabledContainerColor = DarkGreen,
            disabledContentColor = Grey4
        ),
        onClick = { if (enable) onClick.invoke() else null },
        content = content
    )
}