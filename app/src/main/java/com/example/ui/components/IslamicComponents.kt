package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun IslamicTopBar(
  title: String,
  subtitle: String? = null,
  isBanglaSelected: Boolean = true,
  onLanguageToggle: () -> Unit,
  modifier: Modifier = Modifier
) {
  val topBarGradient = Brush.horizontalGradient(
    colors = listOf(Emerald900, Emerald800, Color(0xFF0F4735))
  )

  Surface(
    color = Color.Transparent,
    tonalElevation = 6.dp,
    modifier = modifier
      .fillMaxWidth()
      .background(topBarGradient)
      .statusBarsPadding()
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          )
          Spacer(modifier = Modifier.width(6.dp))
          Surface(
            color = Gold500.copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Gold400.copy(alpha = 0.6f)),
            modifier = Modifier.padding(2.dp)
          ) {
            Text(
              text = "নূরে ইসলাম",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = Gold300,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }
        if (!subtitle.isNullOrBlank()) {
          Text(
            text = subtitle,
            style = MaterialTheme.typography.labelSmall,
            color = Emerald100
          )
        }
      }

      // Language Switcher Chip
      Surface(
        onClick = onLanguageToggle,
        shape = RoundedCornerShape(20.dp),
        color = Emerald700,
        border = androidx.compose.foundation.BorderStroke(1.dp, Gold500.copy(alpha = 0.5f)),
        modifier = Modifier.height(34.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 10.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Language,
            contentDescription = "Language",
            tint = Gold300,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = if (isBanglaSelected) "বাংলা" else "EN",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
          )
        }
      }
    }
  }
}

@Composable
fun IslamicHeroCard(
  title: String,
  subtitle: String,
  arabicHighlight: String? = null,
  badgeText: String? = null,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit = {}
) {
  val gradient = Brush.linearGradient(
    colors = listOf(Emerald900, Emerald800, Color(0xFF0F4735))
  )

  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(20.dp))
      .border(1.dp, Gold500.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(gradient)
        .padding(18.dp)
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Gold300
            )
          )
          if (badgeText != null) {
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Gold500.copy(alpha = 0.2f),
              border = androidx.compose.foundation.BorderStroke(1.dp, Gold400.copy(alpha = 0.5f))
            ) {
              Text(
                text = badgeText,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = Gold300,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }

        if (arabicHighlight != null) {
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = arabicHighlight,
            style = ArabicTextStyle.copy(
              fontSize = 24.sp,
              color = Color.White,
              textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
          )
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = subtitle,
          style = MaterialTheme.typography.bodyMedium.copy(color = Emerald100),
          modifier = Modifier.fillMaxWidth()
        )

        content()
      }
    }
  }
}

@Composable
fun SectionHeader(
  title: String,
  subtitle: String? = null,
  actionText: String? = null,
  onActionClick: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Column {
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
      )
      if (subtitle != null) {
        Text(
          text = subtitle,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
    if (actionText != null && onActionClick != null) {
      Text(
        text = actionText,
        style = MaterialTheme.typography.labelMedium.copy(
          fontWeight = FontWeight.Bold,
          color = Emerald700
        ),
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .clickable { onActionClick() }
          .padding(horizontal = 8.dp, vertical = 4.dp)
      )
    }
  }
}

@Composable
fun AyahCard(
  surahNumber: Int,
  ayahNumber: Int,
  arabicText: String,
  bengaliTranslation: String,
  englishTranslation: String? = null,
  tafsirSummary: String? = null,
  isBookmarked: Boolean,
  isPlaying: Boolean = false,
  showDualLanguage: Boolean = true,
  arabicFontSize: Float = 24f,
  onPlayClick: () -> Unit = {},
  onBookmarkClick: () -> Unit = {},
  onTafsirClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isPlaying) Emerald50 else MaterialTheme.colorScheme.surface
    ),
    border = androidx.compose.foundation.BorderStroke(
      width = if (isPlaying) 1.5.dp else 1.dp,
      color = if (isPlaying) Emerald600 else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
    ),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Verse number header
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(if (isPlaying) Emerald700 else MaterialTheme.colorScheme.primaryContainer)
          ) {
            Text(
              text = "$ayahNumber",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
              color = if (isPlaying) Color.White else MaterialTheme.colorScheme.onPrimaryContainer
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "আয়াত $ayahNumber",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Row {
          IconButton(
            onClick = onPlayClick,
            modifier = Modifier.size(36.dp)
          ) {
            Icon(
              imageVector = Icons.Default.VolumeUp,
              contentDescription = "Recite",
              tint = if (isPlaying) Emerald700 else MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.size(20.dp)
            )
          }
          IconButton(
            onClick = onBookmarkClick,
            modifier = Modifier.size(36.dp)
          ) {
            Icon(
              imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
              contentDescription = "Bookmark",
              tint = if (isBookmarked) Gold500 else MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.size(20.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Arabic Uthmani text with proper line height
      Text(
        text = arabicText,
        style = ArabicTextStyle.copy(
          fontSize = arabicFontSize.sp,
          lineHeight = (arabicFontSize * 1.8f).sp,
          color = MaterialTheme.colorScheme.onSurface,
          textAlign = TextAlign.Right
        ),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Bengali translation
      Text(
        text = bengaliTranslation,
        style = MaterialTheme.typography.bodyLarge.copy(
          color = MaterialTheme.colorScheme.onSurface,
          lineHeight = 22.sp
        ),
        modifier = Modifier.fillMaxWidth()
      )

      // Optional English translation
      if (showDualLanguage && !englishTranslation.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = englishTranslation,
          style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
          ),
          modifier = Modifier.fillMaxWidth()
        )
      }

      // Tafsir button
      if (!tafsirSummary.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
          onClick = onTafsirClick,
          shape = RoundedCornerShape(8.dp),
          color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
          ) {
            Text(
              text = "তাফসীর ও ব্যাখ্যা দেখুন",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = Emerald700
              )
            )
          }
        }
      }
    }
  }
}
