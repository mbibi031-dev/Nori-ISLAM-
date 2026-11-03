package com.example.ui.screens.salah

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.PrayerRakat
import com.example.data.model.SalahStep
import com.example.data.repository.SalahData
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiState

@Composable
fun SalahScreen(
  uiState: UiState,
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val tabs = listOf(
    "নামাজের ধাপসমূহ",
    "ওয়াক্ত ও রাকাতসমূহ",
    "অজু শিক্ষা",
    "গোসল শিক্ষা",
    "নামাজে সাধারণ ভুল",
    "মহিলাদের নামাজ"
  )

  Column(modifier = modifier.fillMaxSize()) {
    // Tabs Navigation
    ScrollableTabRow(
      selectedTabIndex = uiState.selectedSalahTab,
      edgePadding = 16.dp,
      indicator = { tabPositions ->
        TabRowDefaults.SecondaryIndicator(
          modifier = Modifier.tabIndicatorOffset(tabPositions[uiState.selectedSalahTab]),
          color = Emerald700
        )
      },
      containerColor = MaterialTheme.colorScheme.surface
    ) {
      tabs.forEachIndexed { index, title ->
        Tab(
          selected = uiState.selectedSalahTab == index,
          onClick = { viewModel.selectSalahTab(index) },
          text = {
            Text(
              text = title,
              style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (uiState.selectedSalahTab == index) FontWeight.Bold else FontWeight.Normal,
                color = if (uiState.selectedSalahTab == index) Emerald700 else MaterialTheme.colorScheme.onSurfaceVariant
              )
            )
          }
        )
      }
    }

    when (uiState.selectedSalahTab) {
      0 -> SalahStepsTab(uiState = uiState, viewModel = viewModel)
      1 -> PrayerRakatsTab()
      2 -> WuduTutorialTab()
      3 -> GhuslTutorialTab()
      4 -> CommonMistakesTab()
      5 -> WomenSalahTab()
    }
  }
}

@Composable
fun SalahStepsTab(
  uiState: UiState,
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 90.dp, top = 8.dp)
  ) {
    items(SalahData.salahSteps, key = { it.stepNumber }) { step ->
      SalahStepCard(step = step)
    }
  }
}

@Composable
fun SalahStepCard(step: SalahStep, modifier: Modifier = Modifier) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Step Header
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .size(34.dp)
              .clip(CircleShape)
              .background(Emerald700)
          ) {
            Text(
              text = "${step.stepNumber}",
              style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = step.titleBn,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
        }

        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Emerald100
        ) {
          Text(
            text = step.arabicCalligraphy,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald800
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Arabic recitation & Dua
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Emerald50,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = step.arabicDua,
            style = ArabicTextStyle.copy(
              fontSize = 20.sp,
              lineHeight = 36.sp,
              textAlign = TextAlign.Right,
              color = Emerald900
            ),
            modifier = Modifier.fillMaxWidth()
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "উচ্চারণ: ${step.transliterationBn}",
            style = MaterialTheme.typography.bodySmall.copy(
              fontWeight = FontWeight.Medium,
              color = Emerald800
            )
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "অর্থ: ${step.meaningBn}",
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Posture Instructions
      Text(
        text = "শারীরিক ভঙ্গি ও নিয়ম:",
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Bold,
          color = Emerald700
        )
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = step.postureDescriptionBn,
        style = MaterialTheme.typography.bodySmall.copy(
          lineHeight = 20.sp,
          color = MaterialTheme.colorScheme.onSurface
        )
      )

      if (step.commonMistakesBn.isNotBlank()) {
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Color(0xFFFFF3E0),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Warning,
              contentDescription = null,
              tint = Color(0xFFE65100),
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "সতর্কতা: ${step.commonMistakesBn}",
              style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFE65100))
            )
          }
        }
      }
    }
  }
}

@Composable
fun PrayerRakatsTab(modifier: Modifier = Modifier) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp, bottom = 90.dp)
  ) {
    item {
      Text(
        text = "পাঁচ ওয়াক্ত ফরজ ও সুন্নাত নামাজের রাকাত তালিকা",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
      )
      Spacer(modifier = Modifier.height(8.dp))
    }

    items(SalahData.prayerRakats, key = { it.nameBn }) { rakat ->
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 5.dp)
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = rakat.nameBn,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Emerald800
              )
            )
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Emerald700
            ) {
              Text(
                text = "মোট: ${rakat.total} রাকাত",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "ওয়াক্তের সময়: ${rakat.timingBn}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Breakdown pills
          Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            if (rakat.sunnahBefore > 0) {
              RakatPill("সুন্নাত", rakat.sunnahBefore, Gold100, Gold600)
            }
            if (rakat.fard > 0) {
              RakatPill("ফরজ", rakat.fard, Emerald100, Emerald800)
            }
            if (rakat.sunnahAfter > 0) {
              RakatPill("সুন্নাত", rakat.sunnahAfter, Gold100, Gold600)
            }
            if (rakat.witr > 0) {
              RakatPill("বিতর", rakat.witr, Color(0xFFEDE7F6), Color(0xFF4527A0))
            }
            if (rakat.nafl > 0) {
              RakatPill("নফল", rakat.nafl, Color(0xFFE0F2F1), Color(0xFF00695C))
            }
          }
        }
      }
    }
  }
}

@Composable
fun RakatPill(name: String, count: Int, bgColor: Color, textColor: Color) {
  Surface(
    shape = RoundedCornerShape(8.dp),
    color = bgColor
  ) {
    Text(
      text = "$name: $count",
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Bold,
        color = textColor
      ),
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    )
  }
}

@Composable
fun WuduTutorialTab(modifier: Modifier = Modifier) {
  val wudu = SalahData.wuduGuide
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp, bottom = 90.dp)
  ) {
    item {
      Text(
        text = wudu.titleBn,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = Emerald800
        )
      )
      Text(
        text = wudu.subtitleBn,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Spacer(modifier = Modifier.height(12.dp))
    }

    // Fard of Wudu Summary Card
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Emerald50),
        border = androidx.compose.foundation.BorderStroke(1.dp, Emerald300),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "অজুর ৪টি ফরজ (যা না করলে অজু হবে না):",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald900
            )
          )
          Spacer(modifier = Modifier.height(6.dp))
          wudu.fardItemsBn.forEach { fard ->
            Text(
              text = "• $fard",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
            )
          }
        }
      }
      Spacer(modifier = Modifier.height(12.dp))
    }

    // Step by step list
    items(wudu.steps, key = { it.stepNumber }) { step ->
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp)
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = step.titleBn,
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            if (step.isFard) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFFFFEBEE)
              ) {
                Text(
                  text = "ফরজ",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFFC62828),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = step.descriptionBn,
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
          )
        }
      }
    }

    // Invalidators of Wudu
    item {
      Spacer(modifier = Modifier.height(12.dp))
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "যেসব কারণে অজু ভঙ্গ হয়:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Color(0xFFE65100)
            )
          )
          Spacer(modifier = Modifier.height(6.dp))
          wudu.invalidatorsBn.forEach { inv ->
            Text(
              text = "• $inv",
              style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF4E342E))
            )
          }
        }
      }
    }
  }
}

@Composable
fun GhuslTutorialTab(modifier: Modifier = Modifier) {
  val ghusl = SalahData.ghuslGuide
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp, bottom = 90.dp)
  ) {
    item {
      Text(
        text = ghusl.titleBn,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = Emerald800
        )
      )
      Text(
        text = ghusl.subtitleBn,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Spacer(modifier = Modifier.height(12.dp))
    }

    // 3 Fard of Ghusl
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Emerald50),
        border = androidx.compose.foundation.BorderStroke(1.dp, Emerald300),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "গোসলের ৩টি ফরজ:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald900
            )
          )
          Spacer(modifier = Modifier.height(6.dp))
          ghusl.fardItemsBn.forEach { fard ->
            Text(
              text = "• $fard",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
            )
          }
        }
      }
      Spacer(modifier = Modifier.height(12.dp))
    }

    // Step by step
    items(ghusl.steps, key = { it.stepNumber }) { step ->
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp)
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = step.titleBn,
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            if (step.isFard) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFFFFEBEE)
              ) {
                Text(
                  text = "ফরজ",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFFC62828),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = step.descriptionBn,
            style = MaterialTheme.typography.bodySmall
          )
        }
      }
    }
  }
}

@Composable
fun CommonMistakesTab(modifier: Modifier = Modifier) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp, bottom = 90.dp)
  ) {
    item {
      Text(
        text = "নামাজে সাধারণ ভুল ও তার সহীহ সমাধান",
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = Emerald800
        )
      )
      Text(
        text = "রাসুলুল্লাহ ﷺ বলেছেন: 'তোমরা সেভাবে নামাজ পড়ো যেভাবে আমাকে নামাজ পড়তে দেখেছো' (বুখারী)",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Spacer(modifier = Modifier.height(12.dp))
    }

    items(SalahData.commonMistakesBn) { (title, description) ->
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFCCBC)),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 5.dp)
      ) {
        Row(
          modifier = Modifier.padding(14.dp)
        ) {
          Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = null,
            tint = Color(0xFFD32F2F),
            modifier = Modifier.size(22.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = title,
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC62828)
              )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = description,
              style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp)
            )
          }
        }
      }
    }
  }
}

@Composable
fun WomenSalahTab(modifier: Modifier = Modifier) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp, bottom = 90.dp)
  ) {
    item {
      Text(
        text = "মহিলাদের নামাজ শিক্ষা ও সহীহ আদব",
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = Emerald800
        )
      )
      Text(
        text = "শারীরিক গঠন ও শালীনতা সংরক্ষণে ফুকাহায়ে কেরামের নির্দেশিত স্বকীয়তাসমূহ",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Spacer(modifier = Modifier.height(12.dp))
    }

    items(SalahData.womenSalahNotesBn) { (title, description) ->
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 5.dp)
      ) {
        Row(modifier = Modifier.padding(14.dp)) {
          Icon(
            imageVector = Icons.Default.Female,
            contentDescription = null,
            tint = Emerald700,
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = title,
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Emerald800
              )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = description,
              style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp)
            )
          }
        }
      }
    }
  }
}
