package com.example.data.model

// --- PRAYER TIMES ---
data class PrayerTime(
  val nameBn: String,
  val nameEn: String,
  val arabic: String,
  val time: String, // e.g. "05:12 AM"
  val isPassed: Boolean = false,
  val isNext: Boolean = false,
  val rakatInfo: String
)

data class PrayerSchedule(
  val fajr: String = "04:58 AM",
  val sunrise: String = "06:14 AM",
  val dhuhr: String = "11:58 AM",
  val asr: String = "03:45 PM",
  val maghrib: String = "05:42 PM",
  val isha: String = "06:58 PM",
  val tahajjud: String = "03:30 AM",
  val activePrayer: String = "Asr",
  val nextPrayer: String = "Maghrib",
  val nextPrayerCountdown: String = "01h 42m"
)

// --- QURAN MODELS ---
data class Surah(
  val number: Int,
  val nameArabic: String,
  val nameBengali: String,
  val nameEnglish: String,
  val englishMeaning: String,
  val revelationType: RevelationType,
  val totalVerses: Int,
  val audioDuration: String,
  val isBookmarked: Boolean = false
)

enum class RevelationType(val bn: String, val en: String) {
  MAKKI("মাক্কী", "Makki"),
  MADANI("মাদানী", "Madani")
}

data class Ayah(
  val surahNumber: Int,
  val ayahNumber: Int,
  val arabicText: String,
  val bengaliTranslation: String,
  val englishTranslation: String,
  val wordByWord: List<WordTranslation> = emptyList(),
  val tafsirSummary: String = "",
  val isBookmarked: Boolean = false
)

data class WordTranslation(
  val arabic: String,
  val transliteration: String,
  val bengali: String,
  val english: String
)

// --- HADITH MODELS ---
enum class HadithGrade(val bn: String, val en: String) {
  SAHIH("সহীহ (Sahih)", "Sahih"),
  HASAN("হাসান (Hasan)", "Hasan"),
  MUTTAFAAQ_ALAYH("মুত্তাফাকুন আলাইহি", "Agreed Upon")
}

data class HadithBook(
  val id: String,
  val nameBn: String,
  val nameEn: String,
  val nameAr: String,
  val compiler: String,
  val totalHadiths: String,
  val descriptionBn: String
)

data class HadithItem(
  val id: String,
  val bookId: String,
  val bookNameBn: String,
  val hadithNumber: Int,
  val chapterBn: String,
  val chapterEn: String,
  val narratorBn: String,
  val narratorEn: String,
  val arabicText: String,
  val bengaliTranslation: String,
  val englishTranslation: String,
  val grade: HadithGrade,
  val reference: String,
  val isBookmarked: Boolean = false
)

// --- ACADEMY MODELS ---
data class Course(
  val id: String,
  val titleBn: String,
  val titleEn: String,
  val categoryBn: String,
  val level: String, // প্রাথমিক, মধ্যম
  val duration: String,
  val totalLessons: Int,
  val descriptionBn: String,
  val descriptionEn: String,
  val lessons: List<Lesson>,
  val quiz: Quiz,
  val isCompleted: Boolean = false,
  val completedLessons: Set<String> = emptySet(),
  val score: Int = 0
)

data class Lesson(
  val id: String,
  val courseId: String,
  val lessonNumber: Int,
  val titleBn: String,
  val titleEn: String,
  val readTime: String,
  val contentBn: String,
  val keyPointsBn: List<String>,
  val quranOrHadithRef: String = ""
)

data class Quiz(
  val id: String,
  val courseId: String,
  val titleBn: String,
  val passingScore: Int = 80,
  val questions: List<QuizQuestion>
)

data class QuizQuestion(
  val questionBn: String,
  val questionEn: String,
  val optionsBn: List<String>,
  val optionsEn: List<String>,
  val correctOptionIndex: Int,
  val explanationBn: String
)

// --- SALAH (NAMAZ) & PURITY MODELS ---
data class SalahStep(
  val stepNumber: Int,
  val titleBn: String,
  val titleEn: String,
  val arabicCalligraphy: String,
  val arabicDua: String,
  val transliterationBn: String,
  val meaningBn: String,
  val englishMeaning: String,
  val postureDescriptionBn: String,
  val commonMistakesBn: String
)

data class PrayerRakat(
  val nameBn: String,
  val nameEn: String,
  val sunnahBefore: Int = 0,
  val fard: Int = 0,
  val sunnahAfter: Int = 0,
  val nafl: Int = 0,
  val witr: Int = 0,
  val total: Int = 0,
  val timingBn: String
)

data class PurityGuide(
  val id: String,
  val titleBn: String,
  val subtitleBn: String,
  val steps: List<PurityStep>,
  val fardItemsBn: List<String>,
  val sunnahItemsBn: List<String>,
  val invalidatorsBn: List<String>
)

data class PurityStep(
  val stepNumber: Int,
  val titleBn: String,
  val descriptionBn: String,
  val arabicDua: String = "",
  val duaMeaningBn: String = "",
  val isFard: Boolean = false
)

// --- DUA & TASBIH MODELS ---
data class DuaItem(
  val id: String,
  val categoryId: String,
  val titleBn: String,
  val titleEn: String,
  val arabicText: String,
  val transliterationBn: String,
  val bengaliMeaning: String,
  val englishMeaning: String,
  val reference: String,
  val benefitBn: String,
  val repeatCount: Int = 1,
  val isFavorite: Boolean = false
)

data class DuaCategory(
  val id: String,
  val nameBn: String,
  val nameEn: String,
  val iconName: String,
  val totalDuas: Int
)

data class TasbihPreset(
  val id: String,
  val arabic: String,
  val transliterationBn: String,
  val meaningBn: String,
  val defaultTarget: Int = 33,
  val virtuesBn: String
)

data class AsmaulHusna(
  val number: Int,
  val arabic: String,
  val transliterationBn: String,
  val meaningBn: String,
  val meaningEn: String,
  val explanationBn: String
)
