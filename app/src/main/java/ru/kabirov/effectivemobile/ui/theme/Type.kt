package ru.kabirov.effectivemobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.kabirov.effectivemobile.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

)
val sfproFamily = FontFamily(
    Font(R.font.sfpro_medium, FontWeight.Medium),
    Font(R.font.sfpro_regular, FontWeight.Normal),
    Font(R.font.sfpro_semibold, FontWeight.SemiBold)
)

val Title1 = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 22.sp,
    lineHeight = 26.sp
)

val Title2 = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 24.sp
)

val Title3 = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 19.sp
)

val Title4 = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 17.sp
)

val Text1 = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 17.sp
)

val ButtonText1 = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 21.sp
)

val ButtonText2 = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 18.sp
)

val TabText = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp,
    lineHeight = 11.sp
)

val Number = TextStyle(
    fontFamily = sfproFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 7.sp,
    lineHeight = 8.sp
)