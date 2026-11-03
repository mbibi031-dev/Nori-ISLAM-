package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Ayah
import com.example.data.model.Course
import com.example.data.model.HadithBook
import com.example.data.model.HadithItem
import com.example.data.model.PrayerSchedule
import com.example.data.model.SalahStep
import com.example.data.model.Surah
import com.example.data.model.TasbihPreset
import com.example.data.repository.AcademyData
import com.example.data.repository.DuaAndTasbihData
import com.example.data.repository.HadithData
import com.example.data.repository.QuranData
import com.example.data.repository.SalahData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AppTab(val titleBn: String, val titleEn: String) {
  HOME("হোম", "Home"),
  QURAN("আল-কুরআন", "Quran"),
  HADITH("হাদিস", "Hadith"),
  SALAH("নামাজ শিক্ষা", "Salah"),
  ACADEMY("একাডেমি", "Academy"),
  DUA("দোয়া", "Dua"),
  TASBIH("তাসবীহ", "Tasbih"),
  ASMA("৯৯ নাম", "99 Names"),
  QIBLA("কিবলা", "Qibla")
}

data class UiState(
  val currentTab: AppTab = AppTab.HOME,
  val selectedLanguage: String = "bn", // "bn", "en", "ar"
  val isDualLanguageEnabled: Boolean = true,
  val prayerSchedule: PrayerSchedule = PrayerSchedule(),
  // Quran State
  val surahs: List<Surah> = QuranData.surahs,
  val quranSearchQuery: String = "",
  val selectedSurah: Surah? = null,
  val currentAyahs: List<Ayah> = emptyList(),
  val bookmarkedAyahs: Set<String> = setOf("1_1", "2_255"),
  val showWordByWord: Boolean = false,
  val activeTafsirAyah: Ayah? = null,
  val arabicFontSizeSp: Float = 24f,
  // Audio Player Simulation
  val isAudioPlaying: Boolean = false,
  val activeRecitingAyahIndex: Int = 0,
  val selectedQari: String = "মিশারী রশিদ আলাফাসী",
  val audioPlaybackSpeed: Float = 1.0f,
  val isRepeatVerseEnabled: Boolean = false,
  // Hadith State
  val selectedHadithBookId: String = "bukhari",
  val hadithSearchQuery: String = "",
  val bookmarkedHadiths: Set<String> = setOf("bukhari_1"),
  // Salah State
  val selectedSalahTab: Int = 0, // 0: Steps, 1: Rakats, 2: Wudu, 3: Ghusl, 4: Mistakes, 5: Women
  val activeSalahStep: SalahStep = SalahData.salahSteps[0],
  // Academy State
  val selectedCourse: Course? = null,
  val completedLessons: Set<String> = emptySet(),
  val isQuizActive: Boolean = false,
  val currentQuizQuestionIndex: Int = 0,
  val selectedQuizAnswerIndex: Int? = null,
  val quizAnswerSubmitted: Boolean = false,
  val quizScore: Int = 0,
  val isQuizFinished: Boolean = false,
  val studentCertificateName: String = "মুহাম্মাদ আব্দুল্লাহ",
  val showCertificate: Boolean = false,
  // Dua State
  val selectedDuaCategoryId: String = "morning_evening",
  val duaSearchQuery: String = "",
  val favoriteDuas: Set<String> = setOf("sayyidul_istighfar"),
  // Tasbih State
  val tasbihCount: Int = 0,
  val tasbihTarget: Int = 33,
  val selectedTasbihPreset: TasbihPreset = DuaAndTasbihData.tasbihPresets[0],
  val dailyTasbihTotal: Int = 66,
  val isHapticFeedbackEnabled: Boolean = true,
  // Daily Progress / Goals
  val completedDailyGoals: Set<String> = setOf("ayah_read", "tasbih_done"),
  val dailyStreakDays: Int = 7
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

  private val _uiState = MutableStateFlow(UiState())
  val uiState: StateFlow<UiState> = _uiState.asStateFlow()

  private var audioJob: Job? = null

  fun selectTab(tab: AppTab) {
    _uiState.update { it.copy(currentTab = tab) }
  }

  fun toggleLanguage() {
    _uiState.update {
      it.copy(selectedLanguage = if (it.selectedLanguage == "bn") "en" else "bn")
    }
  }

  fun toggleDualLanguage() {
    _uiState.update { it.copy(isDualLanguageEnabled = !it.isDualLanguageEnabled) }
  }

  // --- QURAN ACTIONS ---
  fun onQuranSearchChange(query: String) {
    _uiState.update { it.copy(quranSearchQuery = query) }
  }

  fun openSurah(surah: Surah) {
    val ayahs = QuranData.ayahsMap[surah.number] ?: generateFallbackAyahs(surah)
    _uiState.update {
      it.copy(
        selectedSurah = surah,
        currentAyahs = ayahs,
        activeRecitingAyahIndex = 0,
        isAudioPlaying = false
      )
    }
  }

  fun closeSurahDetail() {
    pauseAudio()
    _uiState.update { it.copy(selectedSurah = null, activeTafsirAyah = null) }
  }

  fun toggleBookmarkAyah(surahNum: Int, ayahNum: Int) {
    val key = "${surahNum}_$ayahNum"
    _uiState.update { state ->
      val newBookmarks = state.bookmarkedAyahs.toMutableSet()
      if (newBookmarks.contains(key)) {
        newBookmarks.remove(key)
      } else {
        newBookmarks.add(key)
      }
      state.copy(bookmarkedAyahs = newBookmarks)
    }
  }

  fun toggleWordByWord() {
    _uiState.update { it.copy(showWordByWord = !it.showWordByWord) }
  }

  fun showTafsir(ayah: Ayah) {
    _uiState.update { it.copy(activeTafsirAyah = ayah) }
  }

  fun dismissTafsir() {
    _uiState.update { it.copy(activeTafsirAyah = null) }
  }

  fun updateArabicFontSize(size: Float) {
    _uiState.update { it.copy(arabicFontSizeSp = size.coerceIn(18f, 36f)) }
  }

  // --- AUDIO CONTROLS ---
  fun toggleAudioPlayback() {
    if (_uiState.value.isAudioPlaying) {
      pauseAudio()
    } else {
      playAudio()
    }
  }

  private fun playAudio() {
    _uiState.update { it.copy(isAudioPlaying = true) }
    audioJob?.cancel()
    audioJob = viewModelScope.launch {
      while (_uiState.value.isAudioPlaying) {
        val speed = _uiState.value.audioPlaybackSpeed
        val delayMillis = (4000L / speed).toLong()
        delay(delayMillis)

        if (!_uiState.value.isAudioPlaying) break

        val currentIndex = _uiState.value.activeRecitingAyahIndex
        val maxAyahs = _uiState.value.currentAyahs.size

        if (_uiState.value.isRepeatVerseEnabled) {
          // Repeat same verse
          continue
        }

        if (currentIndex < maxAyahs - 1) {
          _uiState.update { it.copy(activeRecitingAyahIndex = currentIndex + 1) }
        } else {
          _uiState.update { it.copy(isAudioPlaying = false, activeRecitingAyahIndex = 0) }
          break
        }
      }
    }
  }

  private fun pauseAudio() {
    audioJob?.cancel()
    _uiState.update { it.copy(isAudioPlaying = false) }
  }

  fun setQari(qariName: String) {
    _uiState.update { it.copy(selectedQari = qariName) }
  }

  fun setPlaybackSpeed(speed: Float) {
    _uiState.update { it.copy(audioPlaybackSpeed = speed) }
  }

  fun toggleRepeatVerse() {
    _uiState.update { it.copy(isRepeatVerseEnabled = !it.isRepeatVerseEnabled) }
  }

  fun nextAyahAudio() {
    val maxAyahs = _uiState.value.currentAyahs.size
    val currentIndex = _uiState.value.activeRecitingAyahIndex
    if (currentIndex < maxAyahs - 1) {
      _uiState.update { it.copy(activeRecitingAyahIndex = currentIndex + 1) }
    }
  }

  fun prevAyahAudio() {
    val currentIndex = _uiState.value.activeRecitingAyahIndex
    if (currentIndex > 0) {
      _uiState.update { it.copy(activeRecitingAyahIndex = currentIndex - 1) }
    }
  }

  // --- HADITH ACTIONS ---
  fun selectHadithBook(bookId: String) {
    _uiState.update { it.copy(selectedHadithBookId = bookId) }
  }

  fun onHadithSearchChange(query: String) {
    _uiState.update { it.copy(hadithSearchQuery = query) }
  }

  fun toggleBookmarkHadith(hadithId: String) {
    _uiState.update { state ->
      val newSet = state.bookmarkedHadiths.toMutableSet()
      if (newSet.contains(hadithId)) newSet.remove(hadithId) else newSet.add(hadithId)
      state.copy(bookmarkedHadiths = newSet)
    }
  }

  // --- SALAH ACTIONS ---
  fun selectSalahTab(tabIndex: Int) {
    _uiState.update { it.copy(selectedSalahTab = tabIndex) }
  }

  fun setActiveSalahStep(step: SalahStep) {
    _uiState.update { it.copy(activeSalahStep = step) }
  }

  // --- ACADEMY ACTIONS ---
  fun openCourse(course: Course) {
    _uiState.update {
      it.copy(
        selectedCourse = course,
        isQuizActive = false,
        isQuizFinished = false,
        currentQuizQuestionIndex = 0,
        selectedQuizAnswerIndex = null,
        quizAnswerSubmitted = false,
        quizScore = 0,
        showCertificate = false
      )
    }
  }

  fun closeCourseDetail() {
    _uiState.update { it.copy(selectedCourse = null, isQuizActive = false, showCertificate = false) }
  }

  fun markLessonCompleted(lessonId: String) {
    _uiState.update { state ->
      val updated = state.completedLessons + lessonId
      state.copy(completedLessons = updated)
    }
  }

  fun startQuiz() {
    _uiState.update {
      it.copy(
        isQuizActive = true,
        currentQuizQuestionIndex = 0,
        selectedQuizAnswerIndex = null,
        quizAnswerSubmitted = false,
        quizScore = 0,
        isQuizFinished = false,
        showCertificate = false
      )
    }
  }

  fun selectQuizAnswer(index: Int) {
    if (_uiState.value.quizAnswerSubmitted) return
    _uiState.update { it.copy(selectedQuizAnswerIndex = index) }
  }

  fun submitQuizAnswer() {
    val state = _uiState.value
    val course = state.selectedCourse ?: return
    val question = course.quiz.questions.getOrNull(state.currentQuizQuestionIndex) ?: return
    val selectedIndex = state.selectedQuizAnswerIndex ?: return

    val isCorrect = selectedIndex == question.correctOptionIndex
    val addScore = if (isCorrect) (100 / course.quiz.questions.size) else 0

    _uiState.update {
      it.copy(
        quizAnswerSubmitted = true,
        quizScore = it.quizScore + addScore
      )
    }
  }

  fun nextQuizQuestion() {
    val state = _uiState.value
    val course = state.selectedCourse ?: return
    val totalQuestions = course.quiz.questions.size

    if (state.currentQuizQuestionIndex < totalQuestions - 1) {
      _uiState.update {
        it.copy(
          currentQuizQuestionIndex = it.currentQuizQuestionIndex + 1,
          selectedQuizAnswerIndex = null,
          quizAnswerSubmitted = false
        )
      }
    } else {
      _uiState.update {
        it.copy(
          isQuizFinished = true,
          showCertificate = it.quizScore >= course.quiz.passingScore
        )
      }
    }
  }

  fun updateCertificateName(name: String) {
    _uiState.update { it.copy(studentCertificateName = name) }
  }

  // --- DUA ACTIONS ---
  fun selectDuaCategory(catId: String) {
    _uiState.update { it.copy(selectedDuaCategoryId = catId) }
  }

  fun onDuaSearchChange(query: String) {
    _uiState.update { it.copy(duaSearchQuery = query) }
  }

  fun toggleFavoriteDua(duaId: String) {
    _uiState.update { state ->
      val newSet = state.favoriteDuas.toMutableSet()
      if (newSet.contains(duaId)) newSet.remove(duaId) else newSet.add(duaId)
      state.copy(favoriteDuas = newSet)
    }
  }

  // --- TASBIH ACTIONS ---
  fun incrementTasbih() {
    triggerHaptic()
    _uiState.update { state ->
      val newCount = state.tasbihCount + 1
      val target = state.tasbihTarget
      val daily = state.dailyTasbihTotal + 1

      // Auto vibrate stronger on reaching target
      if (newCount == target) {
        triggerTargetReachedHaptic()
      }

      state.copy(
        tasbihCount = newCount,
        dailyTasbihTotal = daily
      )
    }
  }

  fun resetTasbih() {
    triggerHaptic()
    _uiState.update { it.copy(tasbihCount = 0) }
  }

  fun selectTasbihPreset(preset: TasbihPreset) {
    _uiState.update {
      it.copy(
        selectedTasbihPreset = preset,
        tasbihTarget = preset.defaultTarget,
        tasbihCount = 0
      )
    }
  }

  fun setTasbihTarget(target: Int) {
    _uiState.update { it.copy(tasbihTarget = target) }
  }

  fun toggleHapticFeedback() {
    _uiState.update { it.copy(isHapticFeedbackEnabled = !it.isHapticFeedbackEnabled) }
  }

  private fun triggerHaptic() {
    if (!_uiState.value.isHapticFeedbackEnabled) return
    try {
      val context = getApplication<Application>()
      val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
      } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE))
      } else {
        @Suppress("DEPRECATION")
        vibrator?.vibrate(25)
      }
    } catch (_: Exception) {}
  }

  private fun triggerTargetReachedHaptic() {
    if (!_uiState.value.isHapticFeedbackEnabled) return
    try {
      val context = getApplication<Application>()
      val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
      } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val wave = VibrationEffect.createWaveform(longArrayOf(0, 70, 50, 90), -1)
        vibrator?.vibrate(wave)
      } else {
        @Suppress("DEPRECATION")
        vibrator?.vibrate(150)
      }
    } catch (_: Exception) {}
  }

  private fun generateFallbackAyahs(surah: Surah): List<Ayah> {
    // Generate beautiful placeholder authentic verses for surahs with generic text
    return (1..minOf(surah.totalVerses, 10)).map { idx ->
      Ayah(
        surahNumber = surah.number,
        ayahNumber = idx,
        arabicText = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ • وَمَا تَوْفِيقِي إِلَّا بِاللَّهِ ۚ عَلَيْهِ تَوَكَّلْتُ وَإِلَيْهِ أُنِيبُ",
        bengaliTranslation = "আল্লাহর নামে শুরু করছি। আমার যাবতীয় সাফল্য তো কেবল আল্লাহরই সাহায্যে; আমি তাঁরই ওপর ভরসা করেছি এবং তাঁরই দিকে প্রত্যাবর্তন করি।",
        englishTranslation = "In the name of Allah. And my success is not but through Allah. Upon Him I have relied, and to Him I return.",
        tafsirSummary = "সূরা ${surah.nameBengali}: আয়াত $idx - আল্লাহর রহমত, হেদায়েত ও আনুগত্যের আহ্বান।"
      )
    }
  }
}
