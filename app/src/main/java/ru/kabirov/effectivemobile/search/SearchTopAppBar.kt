package ru.kabirov.effectivemobile.search

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.R
import ru.kabirov.effectivemobile.ui.pxToDp
import ru.kabirov.effectivemobile.ui.theme.Grey2
import ru.kabirov.effectivemobile.ui.theme.Grey4
import ru.kabirov.effectivemobile.ui.theme.Red
import ru.kabirov.effectivemobile.ui.theme.Shadows
import ru.kabirov.effectivemobile.ui.theme.Title4
import ru.kabirov.effectivemobile.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(searchHint: String) {
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth()) {
//        OutlinedTextField(
//            value = "",
//            onValueChange = {},
//            leadingIcon = {
//                IconButton(
//                    modifier = Modifier.padding(start = 4.dp),
//                    onClick = {}
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.search),
//                        tint = Grey4,
//                        contentDescription = ""
//                    )
//                }
//            },
//            placeholder = { Text(searchHint) },
//            textStyle = Title4,
//            singleLine = true,
//            maxLines = 1,
//            modifier = Modifier
//                .weight(1f)
//                .height(40.dp)
//                .padding(end = 12.dp),
//            shape = RoundedCornerShape(8.dp),
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Grey2,
//                focusedContainerColor = Grey2,
//                unfocusedTextColor = Grey4,
//                focusedTextColor = Grey4,
//                focusedLabelColor = Grey4,
//                unfocusedLabelColor = Grey4,
//                errorIndicatorColor = Red,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                cursorColor = Grey4
//            )
//        )

        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .padding(end = 12.dp)
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxSize(),
                value = "",
                onValueChange = {},
                interactionSource = interactionSource,
                enabled = true,
                singleLine = true,
                maxLines = 1, textStyle = Title4
            ) { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = "",
                    innerTextField = innerTextField,
                    singleLine = true,
                    enabled = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Grey2,
                        focusedContainerColor = Grey2,
                        unfocusedTextColor = Grey4,
                        focusedTextColor = Grey4,
                        focusedLabelColor = Grey4,
                        unfocusedLabelColor = Grey4,
                        errorIndicatorColor = Red,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Grey4
                    ), leadingIcon = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.search),
                                tint = Grey4,
                                contentDescription = ""
                            )
                        }
                    }, placeholder = {
                        Text(searchHint, style = Title4)
                    }
                )
            }
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