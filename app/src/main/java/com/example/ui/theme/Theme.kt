package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
  primary = Emerald300,
  onPrimary = Emerald900,
  primaryContainer = Emerald800,
  onPrimaryContainer = Emerald100,
  secondary = Gold400,
  onSecondary = Sand900,
  secondaryContainer = Color(0xFF4A3E16),
  onSecondaryContainer = Gold100,
  tertiary = Emerald400,
  onTertiary = Color.White,
  background = DarkBackground,
  onBackground = Color(0xFFE2E9E4),
  surface = DarkSurface,
  onSurface = Color(0xFFE2E9E4),
  surfaceVariant = DarkSurfaceVariant,
  onSurfaceVariant = Color(0xFFB5C6BE),
  outline = Color(0xFF385247)
)

private val LightColorScheme = lightColorScheme(
  primary = Emerald700,
  onPrimary = Color.White,
  primaryContainer = Emerald100,
  onPrimaryContainer = Emerald900,
  secondary = Gold600,
  onSecondary = Color.White,
  secondaryContainer = Gold100,
  onSecondaryContainer = Color(0xFF4E3700),
  tertiary = AccentTeal,
  onTertiary = Color.White,
  background = LightBackground,
  onBackground = Sand900,
  surface = LightSurface,
  onSurface = Sand900,
  surfaceVariant = LightSurfaceVariant,
  onSurfaceVariant = Color(0xFF47564F),
  outline = Color(0xFFC4D5CB)
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Keep signature Islamic emerald-gold branding consistent
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
