package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.IslamicTopBar
import com.example.ui.screens.academy.AcademyScreen
import com.example.ui.screens.asma.AsmaulHusnaScreen
import com.example.ui.screens.dua.DuaScreen
import com.example.ui.screens.hadith.HadithScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.qibla.QiblaScreen
import com.example.ui.screens.quran.QuranScreen
import com.example.ui.screens.salah.SalahScreen
import com.example.ui.screens.tasbih.TasbihScreen
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald700
import com.example.ui.theme.Emerald800
import com.example.ui.theme.Emerald900
import com.example.ui.theme.Gold300
import com.example.ui.theme.Gold400
import com.example.ui.theme.Gold500
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiState

class MainActivity : ComponentActivity() {

  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          MainAppContent(viewModel = viewModel)
        }
      }
    }
  }
}

@Composable
fun MainAppContent(viewModel: MainViewModel) {
  val uiState by viewModel.uiState.collectAsState()
  var showMoreMenu by remember { mutableStateOf(false) }

  // Custom back navigation handler
  BackHandler(enabled = uiState.currentTab != AppTab.HOME || uiState.selectedSurah != null || uiState.selectedCourse != null) {
    if (uiState.selectedSurah != null) {
      viewModel.closeSurahDetail()
    } else if (uiState.selectedCourse != null) {
      viewModel.closeCourseDetail()
    } else {
      viewModel.selectTab(AppTab.HOME)
    }
  }

  val navItemColors = NavigationBarItemDefaults.colors(
    selectedIconColor = Emerald900,
    selectedTextColor = Gold300,
    indicatorColor = Gold400,
    unselectedIconColor = Emerald100,
    unselectedTextColor = Emerald100.copy(alpha = 0.85f)
  )

  Scaffold(
    containerColor = MaterialTheme.colorScheme.background,
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
    topBar = {
      IslamicTopBar(
        title = when (uiState.currentTab) {
          AppTab.HOME -> "নূরে ইসলাম"
          AppTab.QURAN -> "পবিত্র কুরআনুল কারীম"
          AppTab.HADITH -> "হাদিস সংকলন"
          AppTab.SALAH -> "নামাজ ও অজু শিক্ষা"
          AppTab.ACADEMY -> "ইসলামিক একাডেমি"
          AppTab.DUA -> "হিসনুল মুসলিম দোয়া"
          AppTab.TASBIH -> "ডিজিটাল তাসবীহ"
          AppTab.ASMA -> "আসমাউল হুসনা (৯৯ নাম)"
          AppTab.QIBLA -> "কিবলা কম্পাস"
        },
        subtitle = when (uiState.currentTab) {
          AppTab.HOME -> "প্রতিদিনের ইসলামী জ্ঞান ও আমল"
          AppTab.QURAN -> "বাংলা অনুবাদ, তাফসীর ও তিলাওয়াত"
          AppTab.HADITH -> "বিশুদ্ধ সিহাহ সিত্তাহ সংকলন"
          AppTab.SALAH -> "সহীহ পদ্ধতিতে নামাজ আদায়"
          AppTab.ACADEMY -> "কাঠামোগত কোর্স ও সনদপত্র"
          AppTab.DUA -> "দৈনন্দিন জীবনের বরকতময় দোয়া"
          AppTab.TASBIH -> "অবিরাম জিকির ও তাসবীহ গণনা"
          AppTab.ASMA -> "আল্লাহর পবিত্র গুণবাচক নামসমূহ"
          AppTab.QIBLA -> "সঠিক কিবলার দিক নির্দেশনা"
        },
        isBanglaSelected = uiState.selectedLanguage == "bn",
        onLanguageToggle = { viewModel.toggleLanguage() }
      )
    },
    bottomBar = {
      Box(modifier = Modifier.fillMaxWidth()) {
        NavigationBar(
          containerColor = Emerald900,
          tonalElevation = 8.dp,
          modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
          NavigationBarItem(
            selected = uiState.currentTab == AppTab.HOME,
            onClick = { viewModel.selectTab(AppTab.HOME) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text("হোম", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = navItemColors,
            modifier = Modifier.testTag("tab_home")
          )

          NavigationBarItem(
            selected = uiState.currentTab == AppTab.QURAN,
            onClick = { viewModel.selectTab(AppTab.QURAN) },
            icon = { Icon(imageVector = Icons.Default.MenuBook, contentDescription = "Quran") },
            label = { Text("কুরআন", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = navItemColors,
            modifier = Modifier.testTag("tab_quran")
          )

          NavigationBarItem(
            selected = uiState.currentTab == AppTab.HADITH,
            onClick = { viewModel.selectTab(AppTab.HADITH) },
            icon = { Icon(imageVector = Icons.Default.Book, contentDescription = "Hadith") },
            label = { Text("হাদিস", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = navItemColors,
            modifier = Modifier.testTag("tab_hadith")
          )

          NavigationBarItem(
            selected = uiState.currentTab == AppTab.SALAH,
            onClick = { viewModel.selectTab(AppTab.SALAH) },
            icon = { Icon(imageVector = Icons.Default.Mosque, contentDescription = "Salah") },
            label = { Text("নামাজ", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = navItemColors,
            modifier = Modifier.testTag("tab_salah")
          )

          NavigationBarItem(
            selected = uiState.currentTab == AppTab.ACADEMY,
            onClick = { viewModel.selectTab(AppTab.ACADEMY) },
            icon = { Icon(imageVector = Icons.Default.School, contentDescription = "Academy") },
            label = { Text("একাডেমি", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = navItemColors,
            modifier = Modifier.testTag("tab_academy")
          )

          // More Options Button (Dua, Tasbih, Asma, Qibla)
          NavigationBarItem(
            selected = uiState.currentTab in listOf(AppTab.DUA, AppTab.TASBIH, AppTab.ASMA, AppTab.QIBLA),
            onClick = { showMoreMenu = true },
            icon = { Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "More") },
            label = { Text("আরও", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = navItemColors,
            modifier = Modifier.testTag("tab_more")
          )
        }

        // Dropdown Menu for More Categories
        DropdownMenu(
          expanded = showMoreMenu,
          onDismissRequest = { showMoreMenu = false }
        ) {
          DropdownMenuItem(
            text = { Text("দোয়া ভান্ডার (Dua)") },
            leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null, tint = Emerald700) },
            onClick = {
              viewModel.selectTab(AppTab.DUA)
              showMoreMenu = false
            }
          )
          DropdownMenuItem(
            text = { Text("ডিজিটাল তাসবীহ (Tasbih)") },
            leadingIcon = { Icon(Icons.Default.TouchApp, contentDescription = null, tint = Emerald700) },
            onClick = {
              viewModel.selectTab(AppTab.TASBIH)
              showMoreMenu = false
            }
          )
          DropdownMenuItem(
            text = { Text("আল্লাহর ৯৯ নাম (99 Names)") },
            leadingIcon = { Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Gold500) },
            onClick = {
              viewModel.selectTab(AppTab.ASMA)
              showMoreMenu = false
            }
          )
          DropdownMenuItem(
            text = { Text("কিবলা কম্পাস (Qibla)") },
            leadingIcon = { Icon(Icons.Default.Explore, contentDescription = null, tint = Emerald700) },
            onClick = {
              viewModel.selectTab(AppTab.QIBLA)
              showMoreMenu = false
            }
          )
        }
      }
    },
    modifier = Modifier.fillMaxSize()
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      when (uiState.currentTab) {
        AppTab.HOME -> HomeScreen(
          uiState = uiState,
          onNavigateTab = { tab -> viewModel.selectTab(tab) },
          onOpenSurah = { surahNumber ->
            val surah = uiState.surahs.find { it.number == surahNumber }
            if (surah != null) {
              viewModel.openSurah(surah)
              viewModel.selectTab(AppTab.QURAN)
            }
          }
        )
        AppTab.QURAN -> QuranScreen(uiState = uiState, viewModel = viewModel)
        AppTab.HADITH -> HadithScreen(uiState = uiState, viewModel = viewModel)
        AppTab.SALAH -> SalahScreen(uiState = uiState, viewModel = viewModel)
        AppTab.ACADEMY -> AcademyScreen(uiState = uiState, viewModel = viewModel)
        AppTab.DUA -> DuaScreen(uiState = uiState, viewModel = viewModel)
        AppTab.TASBIH -> TasbihScreen(uiState = uiState, viewModel = viewModel)
        AppTab.ASMA -> AsmaulHusnaScreen()
        AppTab.QIBLA -> QiblaScreen()
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoorEIslamHomePreview() {
  MyApplicationTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background
    ) {
      HomeScreen(
        uiState = UiState(),
        onNavigateTab = {},
        onOpenSurah = {}
      )
    }
  }
}
