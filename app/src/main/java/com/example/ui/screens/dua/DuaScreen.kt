package com.example.ui.screens.dua

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.runtime.remember
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
import com.example.data.model.DuaItem
import com.example.data.repository.DuaAndTasbihData
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiState

@Composable
fun DuaScreen(
  uiState: UiState,
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val clipboardManager = LocalClipboardManager.current

  val filteredDuas = remember(
    uiState.selectedDuaCategoryId,
    uiState.duaSearchQuery,
    uiState.favoriteDuas
  ) {
    DuaAndTasbihData.duas.filter { dua ->
      val matchesCategory = uiState.selectedDuaCategoryId == "all" ||
        uiState.selectedDuaCategoryId == "favorites" ||
        dua.categoryId == uiState.selectedDuaCategoryId

      val matchesFavorites = if (uiState.selectedDuaCategoryId == "favorites") {
        uiState.favoriteDuas.contains(dua.id)
      } else true

      val matchesSearch = uiState.duaSearchQuery.isBlank() ||
        dua.titleBn.contains(uiState.duaSearchQuery, ignoreCase = true) ||
        dua.bengaliMeaning.contains(uiState.duaSearchQuery, ignoreCase = true) ||
        dua.transliterationBn.contains(uiState.duaSearchQuery, ignoreCase = true)

      matchesCategory && matchesFavorites && matchesSearch
    }
  }

  Column(modifier = modifier.fillMaxSize()) {
    // Search Box
    OutlinedTextField(
      value = uiState.duaSearchQuery,
      onValueChange = { viewModel.onDuaSearchChange(it) },
      placeholder = { Text("দোয়া বা বিষয় লিখে অনুসন্ধান করুন...") },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Emerald700)
      },
      trailingIcon = {
        if (uiState.duaSearchQuery.isNotBlank()) {
          IconButton(onClick = { viewModel.onDuaSearchChange("") }) {
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

    // Category Filter Chips
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.padding(bottom = 8.dp)
    ) {
      item {
        FilterChip(
          selected = uiState.selectedDuaCategoryId == "all",
          onClick = { viewModel.selectDuaCategory("all") },
          label = { Text("সকল দোয়া") },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Emerald700,
            selectedLabelColor = Color.White
          )
        )
      }
      item {
        FilterChip(
          selected = uiState.selectedDuaCategoryId == "favorites",
          onClick = { viewModel.selectDuaCategory("favorites") },
          label = { Text("প্রিয় দোয়া") },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Gold600,
            selectedLabelColor = Color.White
          )
        )
      }
      items(DuaAndTasbihData.categories, key = { it.id }) { cat ->
        FilterChip(
          selected = uiState.selectedDuaCategoryId == cat.id,
          onClick = { viewModel.selectDuaCategory(cat.id) },
          label = { Text(cat.nameBn) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Emerald700,
            selectedLabelColor = Color.White
          )
        )
      }
    }

    // Dua List
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(bottom = 90.dp)
    ) {
      items(filteredDuas, key = { it.id }) { dua ->
        val isFavorite = uiState.favoriteDuas.contains(dua.id)
        DuaCard(
          dua = dua,
          isFavorite = isFavorite,
          showDualLanguage = uiState.isDualLanguageEnabled,
          onFavoriteClick = { viewModel.toggleFavoriteDua(dua.id) },
          onCopyClick = {
            clipboardManager.setText(
              AnnotatedString("${dua.titleBn}\n\n${dua.arabicText}\n\nউচ্চারণ: ${dua.transliterationBn}\n\nঅর্থ: ${dua.bengaliMeaning}\n\nসূত্র: ${dua.reference}")
            )
          }
        )
      }
    }
  }
}

@Composable
fun DuaCard(
  dua: DuaItem,
  isFavorite: Boolean,
  showDualLanguage: Boolean,
  onFavoriteClick: () -> Unit,
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
      // Header
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = dua.titleBn,
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald800
            )
          )
          Text(
            text = dua.reference,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        if (dua.repeatCount > 1) {
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Gold100,
            modifier = Modifier.padding(start = 6.dp)
          ) {
            Text(
              text = "${dua.repeatCount} বার",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Gold600
              ),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Arabic text
      Text(
        text = dua.arabicText,
        style = ArabicTextStyle.copy(
          fontSize = 20.sp,
          lineHeight = 36.sp,
          textAlign = TextAlign.Right,
          color = Emerald900
        ),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Transliteration
      Text(
        text = "উচ্চারণ: ${dua.transliterationBn}",
        style = MaterialTheme.typography.bodySmall.copy(
          fontWeight = FontWeight.Medium,
          color = Emerald800,
          lineHeight = 18.sp
        )
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Bengali meaning
      Text(
        text = "অর্থ: ${dua.bengaliMeaning}",
        style = MaterialTheme.typography.bodySmall.copy(
          color = MaterialTheme.colorScheme.onSurface,
          lineHeight = 18.sp
        )
      )

      if (showDualLanguage && dua.englishMeaning.isNotBlank()) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "English: ${dua.englishMeaning}",
          style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        )
      }

      // Benefit / Virtue
      if (dua.benefitBn.isNotBlank()) {
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Emerald50,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "ফজিলত: ${dua.benefitBn}",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Medium,
              color = Emerald900
            ),
            modifier = Modifier.padding(8.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Action row: Copy, Favorite
      Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
      ) {
        IconButton(onClick = onCopyClick, modifier = Modifier.size(36.dp)) {
          Icon(
            imageVector = Icons.Default.ContentCopy,
            contentDescription = "Copy",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
          )
        }
        IconButton(onClick = onFavoriteClick, modifier = Modifier.size(36.dp)) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}
