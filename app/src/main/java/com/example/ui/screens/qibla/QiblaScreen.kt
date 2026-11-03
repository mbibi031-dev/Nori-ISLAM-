package com.example.ui.screens.qibla

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun QiblaScreen(modifier: Modifier = Modifier) {
  // Qibla direction for Dhaka, Bangladesh: ~269° (West-South-West)
  var qiblaAngle by remember { mutableFloatStateOf(269f) }

  val animatedAngle by animateFloatAsState(
    targetValue = qiblaAngle,
    animationSpec = spring(),
    label = "compass"
  )

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    // Location Header Card
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(14.dp)
      ) {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Emerald100)
        ) {
          Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = Emerald700,
            modifier = Modifier.size(22.dp)
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = "বর্তমান অবস্থান: ঢাকা, বাংলাদেশ",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
          Text(
            text = "কিবলার কোণ: ২৬৯° (পশ্চিম-দক্ষিণ-পশ্চিম) • দূরত্ব: ৫,১৬০ কিমি",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    Spacer(modifier = Modifier.weight(0.5f))

    // Interactive Compass Dial Canvas & Indicator
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.size(280.dp)
    ) {
      // Background dial
      Surface(
        shape = CircleShape,
        color = Emerald900,
        border = androidx.compose.foundation.BorderStroke(3.dp, Gold500.copy(alpha = 0.6f)),
        modifier = Modifier.size(280.dp)
      ) {}

      // Compass Cardinal Directions
      Box(modifier = Modifier.size(250.dp)) {
        Text(
          text = "উ (N)",
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5252)
          ),
          modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
          text = "দ (S)",
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
          ),
          modifier = Modifier.align(Alignment.BottomCenter)
        )
        Text(
          text = "পূ (E)",
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
          ),
          modifier = Modifier.align(Alignment.CenterEnd)
        )
        Text(
          text = "প (W)",
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Gold300
          ),
          modifier = Modifier.align(Alignment.CenterStart)
        )
      }

      // Compass Ring Marks
      Canvas(modifier = Modifier.size(220.dp)) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.width / 2
        for (i in 0 until 360 step 30) {
          val rad = Math.toRadians(i.toDouble())
          val start = Offset(
            (center.x + (radius - 12) * Math.cos(rad)).toFloat(),
            (center.y + (radius - 12) * Math.sin(rad)).toFloat()
          )
          val end = Offset(
            (center.x + radius * Math.cos(rad)).toFloat(),
            (center.y + radius * Math.sin(rad)).toFloat()
          )
          drawLine(
            color = if (i % 90 == 0) Gold400 else Color.White.copy(alpha = 0.4f),
            start = start,
            end = end,
            strokeWidth = if (i % 90 == 0) 3f else 1.5f
          )
        }
      }

      // Kaaba Direction Needle
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .size(200.dp)
          .rotate(animatedAngle)
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.fillMaxSize()
        ) {
          // Kaaba Pointer Top
          Icon(
            imageVector = Icons.Default.Mosque,
            contentDescription = "Kaaba Direction",
            tint = Gold400,
            modifier = Modifier.size(36.dp)
          )
          Box(
            modifier = Modifier
              .width(4.dp)
              .height(60.dp)
              .clip(RoundedCornerShape(2.dp))
              .background(Gold500)
          )
          Spacer(modifier = Modifier.weight(1f))
        }
      }

      // Central Pivot
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .size(32.dp)
          .clip(CircleShape)
          .background(Gold500)
          .border(2.dp, Color.White, CircleShape)
      ) {
        Box(
          modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(Emerald900)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Angle Display Pill
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = Emerald50,
      border = androidx.compose.foundation.BorderStroke(1.dp, Emerald300)
    ) {
      Text(
        text = "পবিত্র কাবা শরীফের দিক: ২৬৯°",
        style = MaterialTheme.typography.titleSmall.copy(
          fontWeight = FontWeight.Bold,
          color = Emerald800
        ),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
      )
    }

    Spacer(modifier = Modifier.weight(0.5f))

    // Instruction Box
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 70.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Text(
          text = "কিবলা সঠিকভাবে নির্ণয়ের পরামর্শ:",
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Emerald700
          )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "• ফোনটি একটি সমতল অনুভূমিক পৃষ্ঠে রাখুন।\n• কোনো ধাতব বস্তু বা ইলেকট্রনিক ডিভাইসের চুম্বকীয় ক্ষেত্র থেকে দূরে থাকুন।\n• সূর্যাস্তের দিক বিবেচনা করে পশ্চিম দিকে সামান্য বামে মুখ করে নামাজে দাঁড়ান।",
          style = MaterialTheme.typography.bodySmall.copy(
            lineHeight = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
        )
      }
    }
  }
}
