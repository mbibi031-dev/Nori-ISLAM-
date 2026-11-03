package com.example.ui.screens.tasbih

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.DuaAndTasbihData
import com.example.ui.theme.ArabicTextStyle
import com.example.ui.theme.ArabicTitleStyle
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald50
import com.example.ui.theme.Emerald700
import com.example.ui.theme.Emerald800
import com.example.ui.theme.Emerald900
import com.example.ui.theme.Gold100
import com.example.ui.theme.Gold300
import com.example.ui.theme.Gold400
import com.example.ui.theme.Gold500
import com.example.ui.theme.Gold600
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiState

@Composable
fun TasbihScreen(
  uiState: UiState,
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val currentPreset = uiState.selectedTasbihPreset
  val target = uiState.tasbihTarget
  val count = uiState.tasbihCount
  val progress = if (target > 0) (count % target).toFloat() / target else 0f
  val completedCycles = if (target > 0) count / target else 0

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    // Top Controls: Target Selector & Haptic toggle
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      // Targets
      Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        listOf(33, 99, 100).forEach { tgt ->
          FilterChip(
            selected = target == tgt,
            onClick = { viewModel.setTasbihTarget(tgt) },
            label = { Text("$tgt") },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = Emerald700,
              selectedLabelColor = Color.White
            )
          )
        }
      }

      // Haptic toggle
      IconButton(onClick = { viewModel.toggleHapticFeedback() }) {
        Icon(
          imageVector = Icons.Default.Vibration,
          contentDescription = "Haptic feedback",
          tint = if (uiState.isHapticFeedbackEnabled) Emerald700 else Color.Gray
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Preset Selection Carousel
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(DuaAndTasbihData.tasbihPresets, key = { it.id }) { preset ->
        val isSelected = currentPreset.id == preset.id
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Emerald700 else MaterialTheme.colorScheme.surface
          ),
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isSelected) Emerald800 else MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
          ),
          modifier = Modifier.clickable { viewModel.selectTasbihPreset(preset) }
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
          ) {
            Text(
              text = preset.arabic,
              style = ArabicTextStyle.copy(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                color = if (isSelected) Color.White else Emerald900
              )
            )
            Text(
              text = preset.transliterationBn,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Emerald100 else MaterialTheme.colorScheme.onSurface
              )
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Current Zikr Info Box
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = Emerald50),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(14.dp)
      ) {
        Text(
          text = currentPreset.arabic,
          style = ArabicTitleStyle.copy(
            fontSize = 32.sp,
            color = Emerald900,
            textAlign = TextAlign.Center
          )
        )
        Text(
          text = "${currentPreset.transliterationBn} — ${currentPreset.meaningBn}",
          style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Medium,
            color = Emerald800
          )
        )
        if (currentPreset.virtuesBn.isNotBlank()) {
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "ফজিলত: ${currentPreset.virtuesBn}",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Emerald700,
              textAlign = TextAlign.Center
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.weight(0.5f))

    // Big Tactile Beaded Counter Button
    val gradient = Brush.radialGradient(
      colors = listOf(Color(0xFF13533E), Emerald900, Color(0xFF041B14))
    )

    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .size(240.dp)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = null
        ) {
          viewModel.incrementTasbih()
        }
    ) {
      // Circular progress track
      CircularProgressIndicator(
        progress = progress,
        color = Gold400,
        trackColor = Emerald100.copy(alpha = 0.3f),
        strokeWidth = 10.dp,
        modifier = Modifier.size(240.dp)
      )

      // Inner Touch Circle
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .size(210.dp)
          .clip(CircleShape)
          .background(gradient)
          .border(2.dp, Gold500.copy(alpha = 0.5f), CircleShape)
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = "$count",
            style = MaterialTheme.typography.displayLarge.copy(
              fontSize = 54.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          )
          Text(
            text = "টার্গেট: $target",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.SemiBold,
              color = Gold300
            )
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "স্পর্শ করে জিকির গণনা করুন",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Emerald100.copy(alpha = 0.8f)
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.weight(0.5f))

    // Bottom Stats: Reset, Completed cycles, Daily Total
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
      Column {
        Text(
          text = "পূর্ণ চক্র: $completedCycles বার",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
          text = "আজকের মোট জিকির: ${uiState.dailyTasbihTotal}",
          style = MaterialTheme.typography.labelSmall.copy(color = Emerald700)
        )
      }

      Surface(
        onClick = { viewModel.resetTasbih() },
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Reset",
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "রিসেট",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
          )
        }
      }
    }
  }
}
