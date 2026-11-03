package com.example.ui.screens.quran

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FontDownload
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Ayah
import com.example.data.model.RevelationType
import com.example.data.model.Surah
import com.example.data.repository.QuranData
import com.example.ui.components.AyahCard
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiState

@Composable
fun QuranScreen(
  uiState: UiState,
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  if (uiState.selectedSurah != null) {
    SurahDetailScreen(
      surah = uiState.selectedSurah,
      ayahs = uiState.currentAyahs,
      uiState = uiState,
      viewModel = viewModel,
      onBack = { viewModel.closeSurahDetail() },
      modifier = modifier
    )
  } else {
    SurahCatalogScreen(
      uiState = uiState,
      viewModel = viewModel,
      onSelectSurah = { viewModel.openSurah(it) },
      modifier = modifier
    )
  }
}

@Composable
fun SurahCatalogScreen(
  uiState: UiState,
  viewModel: MainViewModel,
  onSelectSurah: (Surah) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedFilter by remember { mutableStateOf("all") } // "all", "makki", "madani", "bookmarks"

  val filteredSurahs = remember(uiState.surahs, uiState.quranSearchQuery, selectedFilter, uiState.bookmarkedAyahs) {
    uiState.surahs.filter { surah ->
      val matchesSearch = uiState.quranSearchQuery.isBlank() ||
        surah.nameBengali.contains(uiState.quranSearchQuery, ignoreCase = true) ||
        surah.nameEnglish.contains(uiState.quranSearchQuery, ignoreCase = true) ||
        surah.nameArabic.contains(uiState.quranSearchQuery) ||
        surah.number.toString() == uiState.quranSearchQuery.trim()

      val matchesFilter = when (selectedFilter) {
        "makki" -> surah.revelationType == RevelationType.MAKKI
        "madani" -> surah.revelationType == RevelationType.MADANI
        "bookmarks" -> uiState.bookmarkedAyahs.any { it.startsWith("${surah.number}_") }
        else -> true
      }
      matchesSearch && matchesFilter
    }
  }

  Column(modifier = modifier.fillMaxSize()) {
    // Search Box
    OutlinedTextField(
      value = uiState.quranSearchQuery,
      onValueChange = { viewModel.onQuranSearchChange(it) },
      placeholder = { Text("সূরা বা আয়াত নম্বর অনুসন্ধান করুন...") },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Emerald700)
      },
      trailingIcon = {
        if (uiState.quranSearchQuery.isNotBlank()) {
          IconButton(onClick = { viewModel.onQuranSearchChange("") }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
          }
        }
      },
      singleLine = true,
      shape = RoundedCornerShape(16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    )

    // Filter Chips Row
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.padding(bottom = 8.dp)
    ) {
      item {
        FilterChip(
          selected = selectedFilter == "all",
          onClick = { selectedFilter = "all" },
          label = { Text("সকল ১১৪ সূরা") },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Emerald700,
            selectedLabelColor = Color.White
          )
        )
      }
      item {
        FilterChip(
          selected = selectedFilter == "makki",
          onClick = { selectedFilter = "makki" },
          label = { Text("মাক্কী সূরা") },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Emerald700,
            selectedLabelColor = Color.White
          )
        )
      }
      item {
        FilterChip(
          selected = selectedFilter == "madani",
          onClick = { selectedFilter = "madani" },
          label = { Text("মাদানী সূরা") },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Emerald700,
            selectedLabelColor = Color.White
          )
        )
      }
      item {
        FilterChip(
          selected = selectedFilter == "bookmarks",
          onClick = { selectedFilter = "bookmarks" },
          label = { Text("বুকমার্ক") },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Gold600,
            selectedLabelColor = Color.White
          )
        )
      }
    }

    // Surah List
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(bottom = 90.dp)
    ) {
      items(filteredSurahs, key = { it.number }) { surah ->
        SurahListItem(
          surah = surah,
          hasBookmark = uiState.bookmarkedAyahs.any { it.startsWith("${surah.number}_") },
          onClick = { onSelectSurah(surah) }
        )
      }
    }
  }
}

@Composable
fun SurahListItem(
  surah: Surah,
  hasBookmark: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 5.dp)
      .clickable { onClick() }
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.padding(14.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        // Octagonal / Circular verse badge
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Emerald50)
            .border(1.dp, Emerald300, RoundedCornerShape(10.dp))
        ) {
          Text(
            text = "${surah.number}",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = Emerald800
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = surah.nameBengali,
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            if (hasBookmark) {
              Spacer(modifier = Modifier.width(6.dp))
              Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = null,
                tint = Gold500,
                modifier = Modifier.size(14.dp)
              )
            }
          }
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "${surah.nameEnglish} • ${surah.revelationType.bn}",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "• ${surah.totalVerses} আয়াত",
              style = MaterialTheme.typography.labelSmall.copy(color = Emerald700)
            )
          }
        }
      }

      // Arabic Calligraphy Name
      Text(
        text = surah.nameArabic,
        style = ArabicTitleStyle.copy(
          fontSize = 22.sp,
          color = Emerald800
        )
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahDetailScreen(
  surah: Surah,
  ayahs: List<Ayah>,
  uiState: UiState,
  viewModel: MainViewModel,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  var showFontDialog by remember { mutableStateOf(false) }
  var showQariSheet by remember { mutableStateOf(false) }

  Column(modifier = modifier.fillMaxSize()) {
    // Header Bar
    Surface(
      color = Emerald800,
      tonalElevation = 4.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 8.dp, vertical = 8.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(onClick = onBack) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White
            )
          }
          Column {
            Text(
              text = "${surah.number}. ${surah.nameBengali}",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            )
            Text(
              text = "${surah.nameEnglish} • ${surah.revelationType.bn} • ${surah.totalVerses} আয়াত",
              style = MaterialTheme.typography.labelSmall.copy(color = Emerald100)
            )
          }
        }

        Row {
          // Word-by-word toggle
          IconButton(onClick = { viewModel.toggleWordByWord() }) {
            Icon(
              imageVector = Icons.Default.Translate,
              contentDescription = "Word by Word",
              tint = if (uiState.showWordByWord) Gold300 else Color.White
            )
          }
          // Font size slider dialog
          IconButton(onClick = { showFontDialog = true }) {
            Icon(
              imageVector = Icons.Default.FontDownload,
              contentDescription = "Font Settings",
              tint = Color.White
            )
          }
        }
      }
    }

    // Ayahs List
    LazyColumn(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth(),
      contentPadding = PaddingValues(bottom = 110.dp)
    ) {
      // Bismillah Banner (except Surah At-Tawbah)
      if (surah.number != 9) {
        item {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(Emerald50)
              .border(1.dp, Emerald300, RoundedCornerShape(12.dp))
              .padding(14.dp)
          ) {
            Text(
              text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
              style = ArabicTitleStyle.copy(
                fontSize = 24.sp,
                color = Emerald800,
                textAlign = TextAlign.Center
              )
            )
          }
        }
      }

      items(ayahs, key = { "${it.surahNumber}_${it.ayahNumber}" }) { ayah ->
        val isCurrentlyReciting = uiState.isAudioPlaying &&
          ayahs.getOrNull(uiState.activeRecitingAyahIndex)?.ayahNumber == ayah.ayahNumber

        val isBookmarked = uiState.bookmarkedAyahs.contains("${ayah.surahNumber}_${ayah.ayahNumber}")

        AyahCard(
          surahNumber = ayah.surahNumber,
          ayahNumber = ayah.ayahNumber,
          arabicText = ayah.arabicText,
          bengaliTranslation = ayah.bengaliTranslation,
          englishTranslation = ayah.englishTranslation,
          tafsirSummary = ayah.tafsirSummary,
          isBookmarked = isBookmarked,
          isPlaying = isCurrentlyReciting,
          showDualLanguage = uiState.isDualLanguageEnabled,
          arabicFontSize = uiState.arabicFontSizeSp,
          onPlayClick = {
            val idx = ayahs.indexOf(ayah)
            if (idx != -1) {
              viewModel.selectTab(com.example.ui.viewmodel.AppTab.QURAN)
              viewModel.toggleAudioPlayback()
            }
          },
          onBookmarkClick = {
            viewModel.toggleBookmarkAyah(ayah.surahNumber, ayah.ayahNumber)
          },
          onTafsirClick = {
            viewModel.showTafsir(ayah)
          }
        )

        // Optional Word by Word Expansion
        if (uiState.showWordByWord && ayah.wordByWord.isNotEmpty()) {
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Gold100),
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 24.dp, vertical = 4.dp)
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Text(
                text = "শব্দার্থে অনুবাদ (Word by Word):",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = Gold600
                )
              )
              Spacer(modifier = Modifier.height(6.dp))
              ayah.wordByWord.forEach { word ->
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween,
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                ) {
                  Text(
                    text = word.bengali,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                  )
                  Text(
                    text = "(${word.transliteration})",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                  Text(
                    text = word.arabic,
                    style = ArabicTextStyle.copy(fontSize = 18.sp, color = Emerald800)
                  )
                }
              }
            }
          }
        }
      }
    }

    // Bottom Sticky Audio Player Bar
    AudioPlayerBar(
      uiState = uiState,
      currentSurah = surah,
      viewModel = viewModel,
      onOpenQariSheet = { showQariSheet = true }
    )
  }

  // Font Size Dialog
  if (showFontDialog) {
    AlertDialog(
      onDismissRequest = { showFontDialog = false },
      title = { Text("আরবি ফন্ট সাইজ নির্ধারণ") },
      text = {
        Column {
          Text(
            text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            style = ArabicTextStyle.copy(
              fontSize = uiState.arabicFontSizeSp.sp,
              textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
          )
          Spacer(modifier = Modifier.height(16.dp))
          Slider(
            value = uiState.arabicFontSizeSp,
            onValueChange = { viewModel.updateArabicFontSize(it) },
            valueRange = 18f..36f,
            colors = SliderDefaults.colors(thumbColor = Emerald700, activeTrackColor = Emerald700)
          )
          Text(
            text = "সাইজ: ${uiState.arabicFontSizeSp.toInt()} sp",
            style = MaterialTheme.typography.labelMedium
          )
        }
      },
      confirmButton = {
        Button(
          onClick = { showFontDialog = false },
          colors = ButtonDefaults.buttonColors(containerColor = Emerald700)
        ) {
          Text("সম্পন্ন")
        }
      }
    )
  }

  // Tafsir Sheet
  if (uiState.activeTafsirAyah != null) {
    AlertDialog(
      onDismissRequest = { viewModel.dismissTafsir() },
      title = {
        Text("আয়াত ${uiState.activeTafsirAyah.ayahNumber} এর তাফসীর ও সারসংক্ষেপ")
      },
      text = {
        Column {
          Text(
            text = uiState.activeTafsirAyah.arabicText,
            style = ArabicTextStyle.copy(fontSize = 20.sp, textAlign = TextAlign.Right),
            modifier = Modifier.fillMaxWidth()
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = uiState.activeTafsirAyah.bengaliTranslation,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = uiState.activeTafsirAyah.tafsirSummary,
              style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
              modifier = Modifier.padding(12.dp)
            )
          }
        }
      },
      confirmButton = {
        Button(
          onClick = { viewModel.dismissTafsir() },
          colors = ButtonDefaults.buttonColors(containerColor = Emerald700)
        ) {
          Text("বন্ধ করুন")
        }
      }
    )
  }

  // Qari Selector Sheet
  if (showQariSheet) {
    val qaris = listOf(
      "মিশারী রশিদ আলাফাসী",
      "আব্দুল বাসিত আব্দুল সামাদ",
      "সাদ আল-গামদী",
      "মাহের আল-মুআইকলী"
    )
    AlertDialog(
      onDismissRequest = { showQariSheet = false },
      title = { Text("ক্বারী নির্বাচন করুন") },
      text = {
        Column {
          qaris.forEach { qari ->
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier
                .fillMaxWidth()
                .clickable {
                  viewModel.setQari(qari)
                  showQariSheet = false
                }
                .padding(vertical = 10.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = if (uiState.selectedQari == qari) Emerald700 else MaterialTheme.colorScheme.onSurfaceVariant
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = qari,
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontWeight = if (uiState.selectedQari == qari) FontWeight.Bold else FontWeight.Normal,
                  color = if (uiState.selectedQari == qari) Emerald700 else MaterialTheme.colorScheme.onSurface
                )
              )
            }
          }
        }
      },
      confirmButton = {
        TextButton(onClick = { showQariSheet = false }) {
          Text("বাতিল")
        }
      }
    )
  }
}

@Composable
fun AudioPlayerBar(
  uiState: UiState,
  currentSurah: Surah,
  viewModel: MainViewModel,
  onOpenQariSheet: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    color = Emerald900,
    tonalElevation = 8.dp,
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
      // Qari selection button
      Surface(
        onClick = onOpenQariSheet,
        shape = RoundedCornerShape(12.dp),
        color = Emerald800,
        modifier = Modifier.padding(end = 8.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = Gold300,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = uiState.selectedQari.take(8) + "..",
            style = MaterialTheme.typography.labelSmall.copy(color = Color.White)
          )
        }
      }

      // Playback Controls
      Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
          onClick = { viewModel.prevAyahAudio() },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.SkipPrevious,
            contentDescription = "Previous Ayah",
            tint = Color.White
          )
        }

        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(Gold500)
            .clickable { viewModel.toggleAudioPlayback() }
        ) {
          Icon(
            imageVector = if (uiState.isAudioPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
            contentDescription = if (uiState.isAudioPlaying) "Pause" else "Play",
            tint = Emerald900,
            modifier = Modifier.size(28.dp)
          )
        }

        IconButton(
          onClick = { viewModel.nextAyahAudio() },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.SkipNext,
            contentDescription = "Next Ayah",
            tint = Color.White
          )
        }
      }

      // Speed & Repeat toggles
      Row(verticalAlignment = Alignment.CenterVertically) {
        // Repeat
        IconButton(
          onClick = { viewModel.toggleRepeatVerse() },
          modifier = Modifier.size(34.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Repeat,
            contentDescription = "Repeat",
            tint = if (uiState.isRepeatVerseEnabled) Gold300 else Color.White.copy(alpha = 0.6f),
            modifier = Modifier.size(18.dp)
          )
        }

        // Speed
        Surface(
          onClick = {
            val newSpeed = when (uiState.audioPlaybackSpeed) {
              1.0f -> 1.25f
              1.25f -> 0.75f
              else -> 1.0f
            }
            viewModel.setPlaybackSpeed(newSpeed)
          },
          shape = RoundedCornerShape(8.dp),
          color = Emerald800
        ) {
          Text(
            text = "${uiState.audioPlaybackSpeed}x",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Color.White
            ),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
          )
        }
      }
    }
  }
}
