package ru.kabirov.effectivemobile.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnhancedCapsuleTextField(modifier: Modifier = Modifier) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }  // Set this based on validation logic
    var isSuccess by remember { mutableStateOf(false) }  // Set this based on validation logic

    // Dynamic text color
    val textColor by animateColorAsState(
        targetValue = if (isError) Color.Red else Color.Black
    )

    // Wrapping container with padding, shadow, and background gradient
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        // Card container for the capsule TextField
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5) // Light background
            ),
            modifier = Modifier
                .fillMaxWidth()
            //  .padding(8.dp)
        ) {

            // Capsule-shaped BasicTextField with enhancements
            BasicTextField(
                value = textState,
                onValueChange = {
                    textState = it
                    // Add logic for error or success validation
                    isError = textState.text.length < 3 // Example validation for error
                    isSuccess = textState.text.length >= 3 // Example validation for success
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFFBBDEFB), Color(0xFFE3F2FD))
                        ),
                        shape = CircleShape
                    )
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
                    .padding(
                        horizontal = 16.dp
                    ), // Internal padding for a better look
                cursorBrush = SolidColor(if (isError) Color.Red else Color.Blue),
                textStyle = TextStyle(
                    color = textColor,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,  // Align text to the start (left)
                    shadow = Shadow(color = Color.Gray, blurRadius = 1f) // Subtle shadow for text
                ),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (textState.text.isEmpty()) {
                            Text(
                                text = "Enter your text here...",
                                color = Color.Gray,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start // Placeholder text aligned to the start
                            )
                        }
                        innerTextField() // The actual text field
                    }
                }
            )
        }

    }
}