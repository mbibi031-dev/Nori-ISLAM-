package com.example.ui.screens.home

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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.data.model.PrayerSchedule
import com.example.data.repository.DuaAndTasbihData
import com.example.data.repository.HadithData
import com.example.data.repository.QuranData
import com.example.ui.components.IslamicHeroCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.ArabicTextStyle
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald300
import com.example.ui.theme.Emerald50
import com.example.ui.theme.Emerald600
import com.example.ui.theme.Emerald700
import com.example.ui.theme.Emerald800
import com.example.ui.theme.Emerald900
import com.example.ui.theme.Gold100
import com.example.ui.theme.Gold300
import com.example.ui.theme.Gold400
import com.example.ui.theme.Gold500
import com.example.ui.theme.Gold600
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.UiState

data class QuickNavCategory(
  val tab: AppTab,
  val titleBn: String,
  val subtitleBn: String,
  val icon: ImageVector,
  val color: Color
)

@Composable
fun HomeScreen(
  uiState: UiState,
  onNavigateTab: (AppTab) -> Unit,
  onOpenSurah: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val quickCategories = listOf(
    QuickNavCategory(AppTab.QURAN, "আল-কুরআন", "১১৪ সূরা ও তাফসীর", Icons.Default.MenuBook, Emerald700),
    QuickNavCategory(AppTab.HADITH, "হাদিস সম্ভার", "সিহাহ সিত্তাহ সংকলন", Icons.Default.Book, Gold600),
    QuickNavCategory(AppTab.SALAH, "নামাজ শিক্ষা", "চিত্রসহ অজু ও সালাত", Icons.Default.Mosque, Emerald600),
    QuickNavCategory(AppTab.ACADEMY, "একাডেমি", "কোর্স ও সনদ", Icons.Default.School, Color(0xFF1D3557)),
    QuickNavCategory(AppTab.DUA, "দোয়া ভান্ডার", "হিসনুল মুসলিম", Icons.Default.Favorite, Color(0xFF8B2635)),
    QuickNavCategory(AppTab.TASBIH, "ডিজিটাল তাসবীহ", "কাউন্টার ও জিকির", Icons.Default.TouchApp, Color(0xFF028090)),
    QuickNavCategory(AppTab.ASMA, "আল্লাহর ৯৯ নাম", "আসমাউল হুসনা", Icons.Default.AutoAwesome, Gold500),
    QuickNavCategory(AppTab.QIBLA, "কিবলা কম্পাস", "সঠিক দিক নির্ণয়", Icons.Default.Explore, Color(0xFF264653))
  )

  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Top Date & Hijri Banner
    item {
      IslamicDateHeader()
    }

    // Five Daily Prayer Times Card with Countdown
    item {
      PrayerTimesCountdownCard(
        prayerSchedule = uiState.prayerSchedule,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
      )
    }

    // Daily Learning Streak & Goals Tracker
    item {
      DailyLearningProgressTracker(
        streakDays = uiState.dailyStreakDays,
        completedGoals = uiState.completedDailyGoals.size,
        totalGoals = 5,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
      )
    }

    // Quick Categories Section
    item {
      SectionHeader(
        title = "ইসলামিক শিক্ষার বিভাগসমূহ",
        subtitle = "পবিত্র কুরআন, হাদিস ও আমলের পরিপূর্ণ গাইড"
      )
    }

    item {
      CategoryCardsGrid(
        categories = quickCategories,
        onCategoryClick = { onNavigateTab(it) }
      )
    }

    // Today's Quranic Ayah
    item {
      SectionHeader(
        title = "আজকের কুরআনের আয়াত",
        subtitle = "সূরা আল-বাক্বারাহ: ১৮৬",
        actionText = "কুরআন পড়ুন",
        onActionClick = { onNavigateTab(AppTab.QURAN) }
      )
    }

    item {
      DailyAyahCard(
        ayah = QuranData.dailyAyah,
        onReadMoreClick = { onNavigateTab(AppTab.QURAN) },
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
      )
    }

    // Daily Hadith
    item {
      SectionHeader(
        title = "আজকের নির্বাচিত হাদিস",
        subtitle = "সহীহ মুসলিম: ২৫৬৪",
        actionText = "সকল হাদিস",
        onActionClick = { onNavigateTab(AppTab.HADITH) }
      )
    }

    item {
      DailyHadithCard(
        hadith = HadithData.dailyHadith,
        onViewAllClick = { onNavigateTab(AppTab.HADITH) },
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
      )
    }

    // Daily Dua (Sayyidul Istighfar)
    item {
      SectionHeader(
        title = "আজকের শ্রেষ্ঠ দোয়া",
        subtitle = "সাইয়্যিদুল ইস্তিগফার • সহীহ বুখারী",
        actionText = "দোয়া ভান্ডার",
        onActionClick = { onNavigateTab(AppTab.DUA) }
      )
    }

    item {
      DailyDuaCard(
        onOpenDuaClick = { onNavigateTab(AppTab.DUA) },
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
      )
    }

    // Islamic Quote / Reminder Banner
    item {
      IslamicReminderBanner(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
      )
    }
  }
}

@Composable
fun IslamicDateHeader(modifier: Modifier = Modifier) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Emerald50),
    border = androidx.compose.foundation.BorderStroke(1.dp, Emerald300.copy(alpha = 0.5f)),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Mosque,
            contentDescription = null,
            tint = Emerald800,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "১৫ শাওয়াল, ১৪৪৮ হিজরি",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald900
            )
          )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = "ঢাকা, বাংলাদেশ • মঙ্গলবার, ৩ নভেম্বর ২০২৬",
          style = MaterialTheme.typography.labelSmall,
          color = Emerald700
        )
      }

      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Gold100,
        border = androidx.compose.foundation.BorderStroke(1.dp, Gold400.copy(alpha = 0.6f))
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.NotificationsActive,
            contentDescription = "Azan",
            tint = Gold600,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "আজান চালু",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Gold600
          )
        }
      }
    }
  }
}

@Composable
fun PrayerTimesCountdownCard(
  prayerSchedule: PrayerSchedule,
  modifier: Modifier = Modifier
) {
  val gradient = Brush.linearGradient(
    colors = listOf(Emerald900, Emerald800, Color(0xFF114232))
  )

  Card(
    shape = RoundedCornerShape(20.dp),
    modifier = modifier
      .fillMaxWidth()
      .border(1.dp, Gold500.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(gradient)
        .padding(16.dp)
    ) {
      Column {
        // Next prayer countdown header
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column {
            Text(
              text = "পরবর্তী নামাজ: ${prayerSchedule.nextPrayer}",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Gold300
              )
            )
            Text(
              text = "ওয়াক্ত হতে বাকি: ${prayerSchedule.nextPrayerCountdown}",
              style = MaterialTheme.typography.labelMedium.copy(color = Emerald100)
            )
          }

          Surface(
            shape = CircleShape,
            color = Gold500.copy(alpha = 0.2f),
            modifier = Modifier.size(44.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = null,
                tint = Gold300,
                modifier = Modifier.size(24.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Prayer Times Row
        val prayerList = listOf(
          Triple("ফজর", prayerSchedule.fajr, false),
          Triple("যোহর", prayerSchedule.dhuhr, false),
          Triple("আসর", prayerSchedule.asr, true), // Active/Next
          Triple("মাগরিব", prayerSchedule.maghrib, false),
          Triple("ইশা", prayerSchedule.isha, false)
        )

        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          prayerList.forEach { (name, time, isHighlighted) ->
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                  if (isHighlighted) Gold500.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.07f)
                )
                .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
              Text(
                text = name,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.Normal,
                  color = if (isHighlighted) Gold300 else Color.White.copy(alpha = 0.9f)
                )
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = time,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = if (isHighlighted) Color.White else Emerald100
                )
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun DailyLearningProgressTracker(
  streakDays: Int,
  completedGoals: Int,
  totalGoals: Int,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "দৈনিক শিক্ষা অগ্রগতি",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFFFECE5)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Icon(
                imageVector = Icons.Default.LocalFireDepartment,
                contentDescription = null,
                tint = Color(0xFFE65100),
                modifier = Modifier.size(14.dp)
              )
              Text(
                text = "$streakDays দিন স্ট্রিক",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE65100)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
          progress = completedGoals.toFloat() / totalGoals.coerceAtLeast(1),
          color = Emerald600,
          trackColor = Emerald50,
          modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "আজকের লক্ষ্য: $completedGoals/$totalGoals টি সম্পন্ন",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}

@Composable
fun CategoryCardsGrid(
  categories: List<QuickNavCategory>,
  onCategoryClick: (AppTab) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    val chunked = categories.chunked(2)
    chunked.forEach { rowCategories ->
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        rowCategories.forEach { category ->
          Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
            modifier = Modifier
              .weight(1f)
              .clickable { onCategoryClick(category.tab) }
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(12.dp)
            ) {
              Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                  .size(40.dp)
                  .clip(RoundedCornerShape(10.dp))
                  .background(category.color.copy(alpha = 0.12f))
              ) {
                Icon(
                  imageVector = category.icon,
                  contentDescription = category.titleBn,
                  tint = category.color,
                  modifier = Modifier.size(22.dp)
                )
              }
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(
                  text = category.titleBn,
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                  text = category.subtitleBn,
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  maxLines = 1
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun DailyAyahCard(
  ayah: com.example.data.model.Ayah,
  onReadMoreClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, Gold400.copy(alpha = 0.4f)),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Emerald100
        ) {
          Text(
            text = "সূরা আল-বাক্বারাহ • আয়াত ১৮৬",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald800
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Icon(
          imageVector = Icons.Default.Bookmark,
          contentDescription = null,
          tint = Gold500,
          modifier = Modifier.size(18.dp)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = ayah.arabicText,
        style = ArabicTextStyle.copy(
          fontSize = 22.sp,
          lineHeight = 40.sp,
          textAlign = TextAlign.Right
        ),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = ayah.bengaliTranslation,
        style = MaterialTheme.typography.bodyMedium.copy(
          fontWeight = FontWeight.Normal,
          color = MaterialTheme.colorScheme.onSurface,
          lineHeight = 22.sp
        )
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = ayah.englishTranslation,
        style = MaterialTheme.typography.bodySmall.copy(
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      )
    }
  }
}

@Composable
fun DailyHadithCard(
  hadith: com.example.data.model.HadithItem,
  onViewAllClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Gold100
        ) {
          Text(
            text = hadith.reference,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Gold600
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Surface(
          shape = RoundedCornerShape(6.dp),
          color = Emerald100
        ) {
          Text(
            text = hadith.grade.bn,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald700
            ),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = hadith.arabicText,
        style = ArabicTextStyle.copy(
          fontSize = 18.sp,
          lineHeight = 32.sp,
          textAlign = TextAlign.Right
        ),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = hadith.bengaliTranslation,
        style = MaterialTheme.typography.bodyMedium.copy(
          lineHeight = 20.sp,
          color = MaterialTheme.colorScheme.onSurface
        )
      )
    }
  }
}

@Composable
fun DailyDuaCard(
  onOpenDuaClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val dua = DuaAndTasbihData.duas[0] // Sayyidul Istighfar
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = dua.titleBn,
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = Emerald700
          )
        )
        Text(
          text = dua.reference,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = dua.arabicText,
        style = ArabicTextStyle.copy(
          fontSize = 18.sp,
          lineHeight = 34.sp,
          textAlign = TextAlign.Right
        ),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = dua.bengaliMeaning,
        style = MaterialTheme.typography.bodySmall.copy(
          lineHeight = 18.sp,
          color = MaterialTheme.colorScheme.onSurface
        )
      )
    }
  }
}

@Composable
fun IslamicReminderBanner(modifier: Modifier = Modifier) {
  Surface(
    shape = RoundedCornerShape(16.dp),
    color = Emerald50,
    border = androidx.compose.foundation.BorderStroke(1.dp, Emerald300.copy(alpha = 0.4f)),
    modifier = modifier.fillMaxWidth()
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
          .background(Emerald700)
      ) {
        Icon(
          imageVector = Icons.Default.AutoAwesome,
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier.size(20.dp)
        )
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = "রাসুলুল্লাহ ﷺ বলেছেন:",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = Emerald700
        )
        Text(
          text = "“যে ব্যক্তি কুরআন তিলাওয়াত করে এবং অপরকে শিক্ষা দেয় সে-ই তোমাদের মধ্যে সর্বোত্তম।”",
          style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Medium,
            color = Emerald900
          )
        )
      }
    }
  }
}
