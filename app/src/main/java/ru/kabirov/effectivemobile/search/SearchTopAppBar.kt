package ru.kabirov.effectivemobile.search

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.ui.theme.Grey2
import ru.kabirov.effectivemobile.ui.theme.Grey4
import ru.kabirov.effectivemobile.ui.theme.White

@Composable
fun SearchTopAppBar(
    searchHint: String,
    searchString: String,
    searchStringValueChange: (String) -> Unit,
    onLeadingIconClick: () -> Unit,
    isBackIcon: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .padding(end = 12.dp)
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxSize(),
                icon = if (isBackIcon) painterResource(R.drawable.left_arrow) else painterResource(R.drawable.search),
                iconTint = if (isBackIcon) White else Grey4,
                backgroundColor = Grey2,
                shape = RoundedCornerShape(8.dp),
                inputText = searchString,
                onValueChange = { searchStringValueChange(it) },
                placeholder = searchHint,
                onIconClick = onLeadingIconClick
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = Grey2, shape = RoundedCornerShape(10.dp))
        ) {
            IconButton(
                onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.filter),
                    contentDescription = null,
                    tint = White
                )
            }

        }
    }

}