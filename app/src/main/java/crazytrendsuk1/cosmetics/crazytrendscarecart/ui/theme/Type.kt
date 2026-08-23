package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import crazytrendsuk1.cosmetics.crazytrendscarecart.R

private val FontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private val HeadingFont = FontFamily(
    Font(GoogleFont("Playfair Display"), FontProvider, FontWeight.SemiBold),
    Font(GoogleFont("Playfair Display"), FontProvider, FontWeight.Bold),
)

private val BodyFont = FontFamily(
    Font(GoogleFont("Lato"), FontProvider, FontWeight.Normal),
    Font(GoogleFont("Lato"), FontProvider, FontWeight.SemiBold),
    Font(GoogleFont("Lato"), FontProvider, FontWeight.Bold),
)

val AppTypography = Typography(
    displaySmall = TextStyle(fontFamily = HeadingFont, fontWeight = FontWeight.Bold, fontSize = 34.sp, lineHeight = 40.sp),
    headlineMedium = TextStyle(fontFamily = HeadingFont, fontWeight = FontWeight.SemiBold, fontSize = 26.sp, lineHeight = 32.sp),
    titleLarge = TextStyle(fontFamily = HeadingFont, fontWeight = FontWeight.SemiBold, fontSize = 22.sp, lineHeight = 28.sp),
    titleMedium = TextStyle(fontFamily = BodyFont, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp),
    bodyLarge = TextStyle(fontFamily = BodyFont, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = BodyFont, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 21.sp),
    labelLarge = TextStyle(fontFamily = BodyFont, fontWeight = FontWeight.Bold, fontSize = 14.sp, letterSpacing = 0.4.sp),
)

val Typography = AppTypography
