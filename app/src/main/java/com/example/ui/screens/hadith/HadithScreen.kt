package com.example.ui.screens.hadith

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HadithBook
import com.example.data.model.HadithGrade
import com.example.data.model.HadithItem
import com.example.data.repository.HadithData
import com.example.ui.theme.ArabicTextStyle
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald50
import com.example.ui.theme.Emerald700
import com.example.ui.theme.Emerald800
import com.example.ui.theme.Gold100
import com.example.ui.theme.Gold500
import com.example.ui.theme.Gold600
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiState

@Composable
fun HadithScreen(
  uiState: UiState,
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val clipboardManager = LocalClipboardManager.current
  var selectedTab by remember { mutableStateOf("all") } // "all", "bookmarks"

  val filteredHadiths = remember(
    uiState.selectedHadithBookId,
    uiState.hadithSearchQuery,
    selectedTab,
    uiState.bookmarkedHadiths
  ) {
    HadithData.hadiths.filter { hadith ->
      val matchesBook = uiState.selectedHadithBookId == "all" || hadith.bookId == uiState.selectedHadithBookId
      val matchesSearch = uiState.hadithSearchQuery.isBlank() ||
        hadith.bengaliTranslation.contains(uiState.hadithSearchQuery, ignoreCase = true) ||
        hadith.englishTranslation.contains(uiState.hadithSearchQuery, ignoreCase = true) ||
        hadith.chapterBn.contains(uiState.hadithSearchQuery, ignoreCase = true) ||
        hadith.narratorBn.contains(uiState.hadithSearchQuery, ignoreCase = true) ||
        hadith.reference.contains(uiState.hadithSearchQuery, ignoreCase = true)

      val matchesBookmark = if (selectedTab == "bookmarks") {
        uiState.bookmarkedHadiths.contains(hadith.id)
      } else true

      matchesBook && matchesSearch && matchesBookmark
    }
  }

  Column(modifier = modifier.fillMaxSize()) {
    // Search Box
    OutlinedTextField(
      value = uiState.hadithSearchQuery,
      onValueChange = { viewModel.onHadithSearchChange(it) },
      placeholder = { Text("হাদিস অনুসন্ধান করুন (শব্দ, বর্ণনাকারী, নম্বর)...") },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Emerald700)
      },
      trailingIcon = {
        if (uiState.hadithSearchQuery.isNotBlank()) {
          IconButton(onClick = { viewModel.onHadithSearchChange("") }) {
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

    // Hadith Books Horizontal Carousel
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.padding(bottom = 8.dp)
    ) {
      item {
        FilterChip(
          selected = uiState.selectedHadithBookId == "all",
          onClick = { viewModel.selectHadithBook("all") },
          label = { Text("সকল গ্রন্থ") },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Emerald700,
            selectedLabelColor = Color.White
          )
        )
      }
      items(HadithData.books, key = { it.id }) { book ->
        FilterChip(
          selected = uiState.selectedHadithBookId == book.id,
          onClick = { viewModel.selectHadithBook(book.id) },
          label = { Text(book.nameBn) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Emerald700,
            selectedLabelColor = Color.White
          )
        )
      }
    }

    // Selected Book Info Summary Card (if specific book selected)
    val activeBook = HadithData.books.find { it.id == uiState.selectedHadithBookId }
    if (activeBook != null) {
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Emerald50),
        border = androidx.compose.foundation.BorderStroke(1.dp, Emerald100),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 4.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(12.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Book,
            contentDescription = null,
            tint = Emerald700,
            modifier = Modifier.size(28.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "${activeBook.nameBn} (${activeBook.nameAr})",
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
              color = Emerald800
            )
            Text(
              text = "সংকলক: ${activeBook.compiler} • ${activeBook.totalHadiths}",
              style = MaterialTheme.typography.labelSmall,
              color = Emerald700
            )
          }
        }
      }
    }

    // Hadith List
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(bottom = 90.dp)
    ) {
      items(filteredHadiths, key = { it.id }) { hadith ->
        val isBookmarked = uiState.bookmarkedHadiths.contains(hadith.id)
        HadithItemCard(
          hadith = hadith,
          isBookmarked = isBookmarked,
          showDualLanguage = uiState.isDualLanguageEnabled,
          onBookmarkClick = { viewModel.toggleBookmarkHadith(hadith.id) },
          onCopyClick = {
            clipboardManager.setText(
              AnnotatedString(
                "${hadith.bookNameBn} - ${hadith.reference}\n\n${hadith.arabicText}\n\nঅনুবাদ: ${hadith.bengaliTranslation}\n\nমান: ${hadith.grade.bn}"
              )
            )
          }
        )
      }
    }
  }
}

@Composable
fun HadithItemCard(
  hadith: HadithItem,
  isBookmarked: Boolean,
  showDualLanguage: Boolean,
  onBookmarkClick: () -> Unit,
  onCopyClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Header: Reference & Grade Badge
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column {
          Text(
            text = hadith.reference,
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald700
            )
          )
          Text(
            text = "অধ্যায়: ${hadith.chapterBn}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Surface(
          shape = RoundedCornerShape(8.dp),
          color = when (hadith.grade) {
            HadithGrade.SAHIH, HadithGrade.MUTTAFAAQ_ALAYH -> Emerald100
            HadithGrade.HASAN -> Gold100
          }
        ) {
          Text(
            text = hadith.grade.bn,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = when (hadith.grade) {
                HadithGrade.SAHIH, HadithGrade.MUTTAFAAQ_ALAYH -> Emerald800
                HadithGrade.HASAN -> Gold600
              }
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Narrator
      Surface(
        shape = RoundedCornerShape(6.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
      ) {
        Text(
          text = "বর্ণনাকারী: ${hadith.narratorBn}",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Arabic text
      Text(
        text = hadith.arabicText,
        style = ArabicTextStyle.copy(
          fontSize = 19.sp,
          lineHeight = 36.sp,
          textAlign = TextAlign.Right
        ),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Bengali translation
      Text(
        text = hadith.bengaliTranslation,
        style = MaterialTheme.typography.bodyMedium.copy(
          lineHeight = 22.sp,
          color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.fillMaxWidth()
      )

      // Optional English translation
      if (showDualLanguage && hadith.englishTranslation.isNotBlank()) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = hadith.englishTranslation,
          style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 18.sp
          ),
          modifier = Modifier.fillMaxWidth()
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Bottom actions: Copy, Bookmark
      Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
      ) {
        IconButton(onClick = onCopyClick, modifier = Modifier.size(36.dp)) {
          Icon(
            imageVector = Icons.Default.ContentCopy,
            contentDescription = "Copy Hadith",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
          )
        }
        IconButton(onClick = onBookmarkClick, modifier = Modifier.size(36.dp)) {
          Icon(
            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = if (isBookmarked) Gold500 else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}
