package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Rose,
    onPrimary = Porcelain,
    secondary = Teal,
    onSecondary = Porcelain,
    background = Cream,
    onBackground = Ink,
    surface = Porcelain,
    onSurface = Ink,
    surfaceVariant = Chip,
    onSurfaceVariant = Muted,
    outline = Border,
    tertiary = Warning,
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFF8DB8),
    secondary = Color(0xFF60D5C6),
    background = Color(0xFF1C1418),
    surface = Color(0xFF291E24),
    onPrimary = Ink,
    onBackground = Porcelain,
    onSurface = Porcelain,
)

@Composable
fun ProductAppMZFCMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content,
    )
}
