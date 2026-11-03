package com.example.ui.screens.asma

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AsmaulHusna
import com.example.data.repository.DuaAndTasbihData
import com.example.ui.theme.*

@Composable
fun AsmaulHusnaScreen(modifier: Modifier = Modifier) {
  var searchQuery by remember { mutableStateOf("") }

  val filteredNames = remember(searchQuery) {
    DuaAndTasbihData.asmaulHusnaList.filter { name ->
      searchQuery.isBlank() ||
        name.transliterationBn.contains(searchQuery, ignoreCase = true) ||
        name.meaningBn.contains(searchQuery, ignoreCase = true) ||
        name.meaningEn.contains(searchQuery, ignoreCase = true) ||
        name.number.toString() == searchQuery.trim()
    }
  }

  Column(modifier = modifier.fillMaxSize()) {
    // Header Info Card
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = Emerald50),
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Text(
          text = "আসমাউল হুসনা (আল্লাহর ৯৯টি গুণবাচক নাম)",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Emerald800
          )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "রাসুলুল্লাহ ﷺ বলেছেন: 'নিশ্চয়ই আল্লাহর ৯৯টি নাম রয়েছে, যে ব্যক্তি তা মুখস্থ ও অনুধাবন করবে সে জান্নাতে প্রবেশ করবে।' (সহীহ বুখারী: ২৭৩৬)",
          style = MaterialTheme.typography.bodySmall.copy(
            color = Emerald900,
            lineHeight = 18.sp
          )
        )
      }
    }

    // Search Bar
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = { Text("নাম বা অর্থ লিখে অনুসন্ধান করুন...") },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Emerald700)
      },
      trailingIcon = {
        if (searchQuery.isNotBlank()) {
          IconButton(onClick = { searchQuery = "" }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
          }
        }
      },
      singleLine = true,
      shape = RoundedCornerShape(14.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 4.dp)
    )

    // Names List
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(16.dp, bottom = 90.dp)
    ) {
      items(filteredNames, key = { it.number }) { asma ->
        AsmaulHusnaItemCard(asma = asma)
      }
    }
  }
}

@Composable
fun AsmaulHusnaItemCard(asma: AsmaulHusna, modifier: Modifier = Modifier) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 5.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.padding(14.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Emerald100)
        ) {
          Text(
            text = "${asma.number}",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald800
            )
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = asma.transliterationBn,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
          Text(
            text = "${asma.meaningBn} • ${asma.meaningEn}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = asma.explanationBn,
            style = MaterialTheme.typography.bodySmall.copy(
              color = Emerald700,
              lineHeight = 16.sp
            )
          )
        }
      }

      // Arabic text
      Text(
        text = asma.arabic,
        style = ArabicTitleStyle.copy(
          fontSize = 26.sp,
          color = Emerald900
        ),
        modifier = Modifier.padding(start = 8.dp)
      )
    }
  }
}
