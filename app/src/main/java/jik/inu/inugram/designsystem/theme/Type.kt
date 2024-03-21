package jik.inu.inugram.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import jik.inu.inugram.R


val SpoqaHanSansNeo = FontFamily(
    Font(R.font.spoqa_han_sans_neo_bold, FontWeight.Bold),
    Font(R.font.spoqa_han_sans_neo_medium, FontWeight.Medium),
    Font(R.font.spoqa_han_sans_neo_regular, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)