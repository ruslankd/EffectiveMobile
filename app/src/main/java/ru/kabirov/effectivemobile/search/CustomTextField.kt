package ru.kabirov.effectivemobile.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.kabirov.effectivemobile.ui.theme.Grey4
import ru.kabirov.effectivemobile.ui.theme.Text1
import ru.kabirov.effectivemobile.ui.theme.White

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    inputText: String,
    onValueChange: (String) -> Unit,
    icon: Painter? = null,
    iconTint: Color,
    onIconClick: () -> Unit,
    maxCharacter: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = Color.Unspecified,
    shape: Shape = MaterialTheme.shapes.small,
    placeholder: String
) {
    BasicTextField(
        modifier = modifier.background(color = backgroundColor, shape = shape),
        singleLine = true,
        value = inputText,
        textStyle = Text1.copy(color = White),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        cursorBrush = SolidColor(Grey4),
        onValueChange = {
            if (it.length <= maxCharacter) {
                onValueChange(it)
            }
        },
        decorationBox = { innerTextField ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp, horizontal = 8.dp),
                content = {
                    if (icon != null) {
                        Icon(
                            modifier = Modifier.clickable {
                                onIconClick()
                            },
                            painter = icon,
                            contentDescription = null,
                            tint = iconTint
                        )
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart,
                        content = {
                            if (inputText.isEmpty()) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = placeholder,
                                    color = Grey4,
                                    maxLines = 1,
                                    style = Text1
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            )
        }
    )
}