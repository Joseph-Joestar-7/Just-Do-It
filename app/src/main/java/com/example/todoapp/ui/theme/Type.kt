package com.example.todoapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todoapp.R

val Ga_Maamli = FontFamily(Font(R.font.gamaamli_regular))

val AbrilFatface = FontFamily(
    Font(R.font.abrilfatface_regular)
)
val Cabin_Sketch= FontFamily(
    Font(R.font.cabinsketch_bold,FontWeight.Bold),
    )
val Kalam= FontFamily(Font(R.font.kalam_bold,FontWeight.Bold))
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
    ),
    displayMedium = TextStyle(
        fontFamily = Ga_Maamli,
        fontWeight = FontWeight.Normal,
    ),
    displaySmall= TextStyle(
        fontFamily = Cabin_Sketch,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge=TextStyle(fontFamily = Kalam,
        fontWeight = FontWeight.Bold)

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