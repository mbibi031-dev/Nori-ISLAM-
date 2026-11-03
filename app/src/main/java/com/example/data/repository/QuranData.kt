package com.example.data.repository

import com.example.data.model.Ayah
import com.example.data.model.RevelationType
import com.example.data.model.Surah
import com.example.data.model.WordTranslation

object QuranData {

  val surahs: List<Surah> = listOf(
    Surah(1, "الفاتحة", "আল-ফাতিহা", "Al-Fatihah", "The Opening", RevelationType.MAKKI, 7, "00:48"),
    Surah(2, "البقرة", "আল-বাক্বারাহ", "Al-Baqarah", "The Cow", RevelationType.MADANI, 286, "02:05:12"),
    Surah(3, "آل عمران", "আলে ইমরান", "Ali 'Imran", "Family of Imran", RevelationType.MADANI, 200, "01:24:10"),
    Surah(4, "النساء", "আন-নিসা", "An-Nisa", "The Women", RevelationType.MADANI, 176, "01:28:40"),
    Surah(5, "المائدة", "আল-মায়িদাহ", "Al-Ma'idah", "The Table Spread", RevelationType.MADANI, 120, "01:04:15"),
    Surah(6, "الأنعام", "আল-আনআম", "Al-An'am", "The Cattle", RevelationType.MAKKI, 165, "01:10:30"),
    Surah(7, "الأعراف", "আল-আরাফ", "Al-A'raf", "The Heights", RevelationType.MAKKI, 206, "01:22:15"),
    Surah(8, "الأنفال", "আল-আনফাল", "Al-Anfal", "The Spoils of War", RevelationType.MADANI, 75, "00:32:45"),
    Surah(9, "التوبة", "আত-তাওবাহ", "At-Tawbah", "The Repentance", RevelationType.MADANI, 129, "01:05:20"),
    Surah(10, "يونس", "ইউনুস", "Yunus", "Jonah", RevelationType.MAKKI, 109, "00:48:10"),
    Surah(11, "هود", "হুদ", "Hud", "Hud", RevelationType.MAKKI, 123, "00:54:15"),
    Surah(12, "يوسف", "ইউসুফ", "Yusuf", "Joseph", RevelationType.MAKKI, 111, "00:52:40"),
    Surah(13, "الرعد", "আর-রাদ", "Ar-Ra'd", "The Thunder", RevelationType.MADANI, 43, "00:26:30"),
    Surah(14, "إبراهيم", "ইব্রাহিম", "Ibrahim", "Abraham", RevelationType.MAKKI, 52, "00:28:15"),
    Surah(15, "الحجر", "আল-হিজর", "Al-Hijr", "The Rocky Tract", RevelationType.MAKKI, 99, "00:27:00"),
    Surah(16, "النحل", "আন-নাহল", "An-Nahl", "The Bee", RevelationType.MAKKI, 128, "00:56:45"),
    Surah(17, "الإسراء", "আল-ইসরা", "Al-Isra", "The Night Journey", RevelationType.MAKKI, 111, "00:48:30"),
    Surah(18, "الكهف", "আল-কাহফ", "Al-Kahf", "The Cave", RevelationType.MAKKI, 110, "00:49:15"),
    Surah(19, "مريم", "মারইয়াম", "Maryam", "Mary", RevelationType.MAKKI, 98, "00:38:20"),
    Surah(20, "طه", "ত্বা-হা", "Ta-Ha", "Ta-Ha", RevelationType.MAKKI, 135, "00:44:10"),
    Surah(21, "الأنبياء", "আল-আম্বিয়া", "Al-Anbiya", "The Prophets", RevelationType.MAKKI, 112, "00:42:15"),
    Surah(22, "الحج", "আল-হজ্জ", "Al-Hajj", "The Pilgrimage", RevelationType.MADANI, 78, "00:41:00"),
    Surah(23, "المؤمنون", "আল-মুমিনুন", "Al-Mu'minun", "The Believers", RevelationType.MAKKI, 118, "00:39:20"),
    Surah(24, "النور", "আন-নূর", "An-Nur", "The Light", RevelationType.MADANI, 64, "00:40:15"),
    Surah(25, "الفرقان", "আল-ফুরকান", "Al-Furqan", "The Criterion", RevelationType.MAKKI, 77, "00:31:00"),
    Surah(26, "الشعراء", "আশ-শুয়ারা", "Ash-Shu'ara", "The Poets", RevelationType.MAKKI, 227, "00:46:10"),
    Surah(27, "النمل", "আন-নামল", "An-Naml", "The Ant", RevelationType.MAKKI, 93, "00:38:40"),
    Surah(28, "القصص", "আল-কাসাস", "Al-Qasas", "The Stories", RevelationType.MAKKI, 88, "00:45:15"),
    Surah(29, "العنكبوت", "আল-আনকাবুত", "Al-'Ankabut", "The Spider", RevelationType.MAKKI, 69, "00:34:10"),
    Surah(30, "الروم", "আর-রূম", "Ar-Rum", "The Romans", RevelationType.MAKKI, 60, "00:29:20"),
    Surah(31, "لقمان", "লুকমান", "Luqman", "Luqman", RevelationType.MAKKI, 34, "00:20:15"),
    Surah(32, "السجدة", "আস-সাজদাহ", "As-Sajdah", "The Prostration", RevelationType.MAKKI, 30, "00:15:40"),
    Surah(33, "الأحزاب", "আল-আহযাব", "Al-Ahzab", "The Combined Forces", RevelationType.MADANI, 73, "00:46:30"),
    Surah(34, "سبأ", "সাবা", "Saba", "Sheba", RevelationType.MAKKI, 54, "00:31:45"),
    Surah(35, "فاطر", "ফাতির", "Fatir", "Originator", RevelationType.MAKKI, 45, "00:27:10"),
    Surah(36, "يس", "ইয়া-সীন", "Ya-Sin", "Ya-Sin", RevelationType.MAKKI, 83, "00:22:45"),
    Surah(37, "الصافات", "আস-সাফফাত", "As-Saffat", "Those Who Set The Ranks", RevelationType.MAKKI, 182, "00:38:10"),
    Surah(38, "ص", "ছোয়াদ", "Sad", "The Letter Sad", RevelationType.MAKKI, 88, "00:28:40"),
    Surah(39, "الزمر", "আজ-জুমার", "Az-Zumar", "The Troops", RevelationType.MAKKI, 75, "00:43:20"),
    Surah(40, "غافر", "গাফির", "Ghafir", "The Forgiver", RevelationType.MAKKI, 85, "00:41:15"),
    Surah(41, "فصلت", "ফুসসিলাত", "Fussilat", "Explained In Detail", RevelationType.MAKKI, 54, "00:30:25"),
    Surah(42, "الشورى", "আশ-শুরা", "Ash-Shura", "The Consultation", RevelationType.MAKKI, 53, "00:32:10"),
    Surah(43, "الزخرف", "আজ-জু Ruf", "Az-Zukhruf", "The Ornaments of Gold", RevelationType.MAKKI, 89, "00:34:50"),
    Surah(44, "الدخان", "আদ-দুখান", "Ad-Dukhan", "The Smoke", RevelationType.MAKKI, 59, "00:16:30"),
    Surah(45, "الجاثية", "আল-জাসিয়া", "Al-Jathiyah", "The Crouching", RevelationType.MAKKI, 37, "00:18:20"),
    Surah(46, "الأحقاف", "আল-আহকাফ", "Al-Ahqaf", "The Curved Sand-hills", RevelationType.MAKKI, 35, "00:24:10"),
    Surah(47, "محمد", "মুহাম্মদ", "Muhammad", "Muhammad", RevelationType.MADANI, 38, "00:21:40"),
    Surah(48, "الفتح", "আল-ফাতহ", "Al-Fath", "The Victory", RevelationType.MADANI, 29, "00:21:15"),
    Surah(49, "الحجرات", "আল-হুজুরাত", "Al-Hujurat", "The Rooms", RevelationType.MADANI, 18, "00:14:30"),
    Surah(50, "ق", "ক্বাফ", "Qaf", "The Letter Qaf", RevelationType.MAKKI, 45, "00:17:20"),
    Surah(51, "الذاريات", "আয-যারিয়াত", "Adh-Dhariyat", "The Winnowing Winds", RevelationType.MAKKI, 60, "00:18:10"),
    Surah(52, "الطور", "আত-তুর", "At-Tur", "The Mount", RevelationType.MAKKI, 49, "00:16:40"),
    Surah(53, "النجم", "আন-নাজম", "An-Najm", "The Star", RevelationType.MAKKI, 62, "00:16:50"),
    Surah(54, "القمر", "আল-ক্বামার", "Al-Qamar", "The Moon", RevelationType.MAKKI, 55, "00:17:15"),
    Surah(55, "الرحمن", "আর-রাহমান", "Ar-Rahman", "The Beneficent", RevelationType.MADANI, 78, "00:21:30"),
    Surah(56, "الواقعة", "আল-ওয়াক্বিয়াহ", "Al-Waqi'ah", "The Inevitable", RevelationType.MAKKI, 96, "00:19:40"),
    Surah(57, "الحديد", "আল-হাদিদ", "Al-Hadid", "The Iron", RevelationType.MADANI, 29, "00:26:10"),
    Surah(58, "المجادلة", "আল-মুজাদালাহ", "Al-Mujadila", "The Pleading Woman", RevelationType.MADANI, 22, "00:19:20"),
    Surah(59, "الحشر", "আল-হাশর", "Al-Hashr", "The Exile", RevelationType.MADANI, 24, "00:20:15"),
    Surah(60, "الممتحنة", "আল-মুমতাহিনাহ", "Al-Mumtahanah", "She That Is To Be Examined", RevelationType.MADANI, 13, "00:15:30"),
    Surah(61, "الصف", "আস-সফ", "As-Saff", "The Ranks", RevelationType.MADANI, 14, "00:10:45"),
    Surah(62, "الجمعة", "আল-জুমুআহ", "Al-Jumu'ah", "Friday", RevelationType.MADANI, 11, "00:08:20"),
    Surah(63, "المنافقون", "আল-মুনাফিকুন", "Al-Munafiqun", "The Hypocrites", RevelationType.MADANI, 11, "00:08:45"),
    Surah(64, "التغابن", "আত-তাগাবুন", "At-Taghabun", "Mutual Disillusion", RevelationType.MADANI, 18, "00:12:10"),
    Surah(65, "الطلاق", "আত-ত্বালাক্ব", "At-Talaq", "The Divorce", RevelationType.MADANI, 12, "00:11:30"),
    Surah(66, "التحريم", "আত-তাহরীম", "At-Tahrim", "The Prohibition", RevelationType.MADANI, 12, "00:12:40"),
    Surah(67, "الملك", "আল-মুলক", "Al-Mulk", "The Sovereignty", RevelationType.MAKKI, 30, "00:11:15"),
    Surah(68, "القلم", "আল-ক্বলম", "Al-Qalam", "The Pen", RevelationType.MAKKI, 52, "00:15:30"),
    Surah(69, "الحاقة", "আল-হাক্কাহ", "Al-Haqqah", "The Reality", RevelationType.MAKKI, 52, "00:14:20"),
    Surah(70, "المعارج", "আল-মাআরিজ", "Al-Ma'arij", "The Ascending Stairways", RevelationType.MAKKI, 44, "00:12:45"),
    Surah(71, "نوح", "নূহ", "Nuh", "Noah", RevelationType.MAKKI, 28, "00:10:15"),
    Surah(72, "الجن", "আল-জ্বিন", "Al-Jinn", "The Jinn", RevelationType.MAKKI, 28, "00:11:40"),
    Surah(73, "المزمل", "আল-মুযযাম্মিল", "Al-Muzzammil", "The Enshrouded One", RevelationType.MAKKI, 20, "00:09:10"),
    Surah(74, "المدثر", "আল-মুদ্দাসসির", "Al-Muddaththir", "The Cloaked One", RevelationType.MAKKI, 56, "00:12:20"),
    Surah(75, "القيامة", "আল-ক্বিয়ামাহ", "Al-Qiyamah", "The Resurrection", RevelationType.MAKKI, 40, "00:08:45"),
    Surah(76, "الإنسان", "আল-ইনসান", "Al-Insan", "Man", RevelationType.MADANI, 31, "00:12:15"),
    Surah(77, "المرسلات", "আল-মুরসালাত", "Al-Mursalat", "The Emissaries", RevelationType.MAKKI, 50, "00:10:30"),
    Surah(78, "النبأ", "আন-নাবা", "An-Naba", "The Tidings", RevelationType.MAKKI, 40, "00:09:20"),
    Surah(79, "النازعات", "আন-নাযিআত", "An-Nazi'at", "Those Who Drag Forth", RevelationType.MAKKI, 46, "00:09:40"),
    Surah(80, "عبس", "আবাসা", "'Abasa", "He Frowned", RevelationType.MAKKI, 42, "00:07:30"),
    Surah(81, "التكوير", "আত-তাকভীর", "At-Takwir", "The Overthrowing", RevelationType.MAKKI, 29, "00:06:15"),
    Surah(82, "الانفطار", "আল-ইনফিতার", "Al-Infitar", "The Cleaving", RevelationType.MAKKI, 19, "00:04:40"),
    Surah(83, "المطففين", "আল-মুতাফফিফীন", "Al-Mutaffifin", "Defrauding", RevelationType.MAKKI, 36, "00:09:10"),
    Surah(84, "الانشقاق", "আল-ইনশিক্বাক্ব", "Al-Inshiqaq", "The Splitting Open", RevelationType.MAKKI, 25, "00:05:45"),
    Surah(85, "البروج", "আল-বুরূজ", "Al-Buruj", "The Mansions of the Stars", RevelationType.MAKKI, 22, "00:06:20"),
    Surah(86, "الطارق", "আত-ত্বারিক্ব", "At-Tariq", "The Morning Star", RevelationType.MAKKI, 17, "00:04:15"),
    Surah(87, "الأعلى", "আল-আলা", "Al-A'la", "The Most High", RevelationType.MAKKI, 19, "00:04:30"),
    Surah(88, "الغاشية", "আল-গাশিয়াহ", "Al-Ghashiyah", "The Overwhelming Event", RevelationType.MAKKI, 26, "00:05:50"),
    Surah(89, "الفجر", "আল-ফাজর", "Al-Fajr", "The Dawn", RevelationType.MAKKI, 30, "00:07:40"),
    Surah(90, "البلد", "আল-বালাদ", "Al-Balad", "The City", RevelationType.MAKKI, 20, "00:04:50"),
    Surah(91, "الشمس", "আশ-শামস", "Ash-Shams", "The Sun", RevelationType.MAKKI, 15, "00:03:40"),
    Surah(92, "الليل", "আল-লাইল", "Al-Layl", "The Night", RevelationType.MAKKI, 21, "00:04:20"),
    Surah(93, "الضحى", "আদ্ব-দুহা", "Ad-Duha", "The Morning Brightness", RevelationType.MAKKI, 11, "00:02:45"),
    Surah(94, "الشرح", "আশ-শারহ", "Ash-Sharh", "The Expansion", RevelationType.MAKKI, 8, "00:01:50"),
    Surah(95, "التين", "আত-তীন", "At-Tin", "The Fig", RevelationType.MAKKI, 8, "00:02:10"),
    Surah(96, "العلق", "আল-আলাক্ব", "Al-'Alaq", "The Clot", RevelationType.MAKKI, 19, "00:04:30"),
    Surah(97, "القدر", "আল-ক্বদর", "Al-Qadr", "The Night of Decree", RevelationType.MAKKI, 5, "00:01:45"),
    Surah(98, "البينة", "আল-বায়্যিনাহ", "Al-Bayyinah", "The Clear Evidence", RevelationType.MADANI, 8, "00:03:50"),
    Surah(99, "الزلزلة", "আয-যালযালাহ", "Az-Zalzalah", "The Earthquake", RevelationType.MADANI, 8, "00:02:15"),
    Surah(100, "العاديات", "আল-আদিয়াত", "Al-'Adiyat", "The Courser", RevelationType.MAKKI, 11, "00:02:30"),
    Surah(101, "القارعة", "আল-ক্বারিয়াহ", "Al-Qari'ah", "The Calamity", RevelationType.MAKKI, 11, "00:02:20"),
    Surah(102, "التكاثر", "আত-তাকাসুর", "At-Takathur", "The Rivalry in World Increase", RevelationType.MAKKI, 8, "00:02:10"),
    Surah(103, "العصر", "আল-আসর", "Al-'Asr", "The Declining Day", RevelationType.MAKKI, 3, "00:01:10"),
    Surah(104, "الهمزة", "আল-হুমাযাহ", "Al-Humazah", "The Scorner", RevelationType.MAKKI, 9, "00:02:15"),
    Surah(105, "الفيل", "আল-ফীল", "Al-Fil", "The Elephant", RevelationType.MAKKI, 5, "00:01:40"),
    Surah(106, "قريش", "কুরাইশ", "Quraysh", "Quraysh", RevelationType.MAKKI, 4, "00:01:25"),
    Surah(107, "الماعون", "আল-মাউন", "Al-Ma'un", "Small Kindnesses", RevelationType.MAKKI, 7, "00:02:00"),
    Surah(108, "الكوثر", "আল-কাউসার", "Al-Kawthar", "Abundance", RevelationType.MAKKI, 3, "00:01:05"),
    Surah(109, "الكافرون", "আল-কাফিরুন", "Al-Kafirun", "The Disbelievers", RevelationType.MAKKI, 6, "00:02:10"),
    Surah(110, "النصر", "আন-নাসর", "An-Nasr", "Divine Support", RevelationType.MADANI, 3, "00:01:15"),
    Surah(111, "المسد", "আল-লাহাব", "Al-Masad", "The Palm Fiber", RevelationType.MAKKI, 5, "00:01:30"),
    Surah(112, "الإخلاص", "আল-ইখলাস", "Al-Ikhlas", "The Sincerity", RevelationType.MAKKI, 4, "00:01:00"),
    Surah(113, "الفلق", "আল-ফালাক্ব", "Al-Falaq", "The Daybreak", RevelationType.MAKKI, 5, "00:01:20"),
    Surah(114, "الناس", "আন-নাস", "An-Nas", "Mankind", RevelationType.MAKKI, 6, "00:01:30")
  )

  // Full Rich Ayah sets for foundational Surahs
  val ayahsMap: Map<Int, List<Ayah>> = mapOf(
    1 to listOf(
      Ayah(
        1, 1,
        "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
        "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।",
        "In the name of Allah, the Entirely Merciful, the Especially Merciful.",
        listOf(
          WordTranslation("بِسْمِ", "Bismi", "নামে", "In the name"),
          WordTranslation("اللَّهِ", "Allahi", "আল্লাহর", "of Allah"),
          WordTranslation("الرَّحْمَٰنِ", "Ar-Rahmani", "পরম করুণাময়", "the Entirely Merciful"),
          WordTranslation("الرَّحِيمِ", "Ar-Rahimi", "অতি দয়ালু", "the Especially Merciful")
        ),
        "তাফসীর ইবনে কাসীর: যেকোনো বরকতময় কাজ আল্লাহর নামে শুরু করা রাসুলুল্লাহ ﷺ এর পবিত্র সুন্নাত। আল্লাহর রহমত ও করুণার ঘোষণা দিয়ে এ পবিত্র কালাম শুরু হয়েছে।"
      ),
      Ayah(
        1, 2,
        "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
        "যাবতীয় প্রশংসা শুধুমাত্র আল্লাহ তাআলার জন্য, যিনি সমগ্র বিশ্বজগতের পালনকর্তা।",
        "[All] praise is [due] to Allah, Lord of the worlds -",
        listOf(
          WordTranslation("الْحَمْدُ", "Al-hamdu", "যাবতীয় প্রশংসা", "All praise"),
          WordTranslation("لِلَّهِ", "lillahi", "আল্লাহর জন্য", "is to Allah"),
          WordTranslation("رَبِّ", "Rabbi", "পালনকর্তা / প্রভু", "Lord"),
          WordTranslation("الْعَالَمِينَ", "al-'alamin", "সমগ্র জাহানের", "of the worlds")
        ),
        "তাফসীর: সমস্ত সৃষ্টির প্রতিটি অণু-পরমাণু আল্লাহর প্রতি কৃতজ্ঞ। 'রব্বুল আলামিন' দ্বারা দৃশ্য ও অদৃশ্য সব জগতের সার্বভৌম মালিক ও প্রতিপালক বোঝানো হয়েছে।"
      ),
      Ayah(
        1, 3,
        "الرَّحْمَٰنِ الرَّحِيمِ",
        "যিনি পরম করুণাময়, অতি দয়ালু।",
        "The Entirely Merciful, the Especially Merciful,",
        listOf(
          WordTranslation("الرَّحْمَٰنِ", "Ar-Rahmani", "পরম দয়ালু", "Entirely Merciful"),
          WordTranslation("الرَّحِيمِ", "Ar-Rahimi", "অফুরন্ত করুণাময়", "Especially Merciful")
        ),
        "তাফসীর: রহমান হলেন তিনি যার দয়া সকল সৃষ্টির ওপর পরিব্যাপ্ত। রহিম হলেন তিনি যিনি পরকালে মুমিনদের প্রতি বিশেষ দয়া প্রদর্শন করবেন।"
      ),
      Ayah(
        1, 4,
        "مَالِكِ يَوْمِ الدِّينِ",
        "যিনি বিচার দিবসের একমাত্র মালিক ও অধিপতি।",
        "Sovereign of the Day of Recompense.",
        listOf(
          WordTranslation("مَالِكِ", "Maliki", "মালিক / অধিপতি", "Master / Sovereign"),
          WordTranslation("يَوْمِ", "Yawmi", "দিবসের", "of the Day"),
          WordTranslation("الدِّينِ", "ad-Deen", "প্রতিদান / বিচারের", "of Recompense")
        ),
        "তাফসীর: কিয়ামতের দিন কেবল আল্লাহর আদেশ ও শাসনই প্রকাশ্যে থাকবে, কোনো জালিম বা অহংকারী সেখানে দাবিদার হতে পারবে না।"
      ),
      Ayah(
        1, 5,
        "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
        "আমরা শুধুমাত্র আপনারই ইবাদত করি এবং শুধুমাত্র আপনারই কাছে সাহায্য প্রার্থনা করি।",
        "It is You we worship and You we ask for help.",
        listOf(
          WordTranslation("إِيَّاكَ", "Iyyaka", "আপনারই", "You alone"),
          WordTranslation("نَعْبُدُ", "na'budu", "আমরা ইবাদত করি", "we worship"),
          WordTranslation("وَإِيَّاكَ", "wa iyyaka", "এবং আপনারই কাছে", "and You alone"),
          WordTranslation("نَسْتَعِينُ", "nasta'een", "আমরা সাহায্য চাই", "we ask for help")
        ),
        "তাফসীর: তাওহীদের মূল নির্যাস। ইবাদতের ক্ষেত্রে কাউকে অংশীদার না করা এবং যাবতীয় সাহায্য কেবলমাত্র এক আল্লাহর কাছে চাওয়া।"
      ),
      Ayah(
        1, 6,
        "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ",
        "আমাদের সরল-সঠিক পথ প্রদর্শন করুন।",
        "Guide us to the straight path -",
        listOf(
          WordTranslation("اهْدِنَا", "Ihdina", "আমাদের পরিচালনা করুন", "Guide us"),
          WordTranslation("الصِّرَاطَ", "as-Sirata", "পথে", "the path"),
          WordTranslation("الْمُسْتَقِيمَ", "al-Mustaqeem", "সরল / সঠিক", "straight")
        ),
        "তাফসীর: সিরাতুল মুস্তাকীম হলো কোরআন ও সুন্নাহর পথ, যা বান্দাকে জান্নাতে পৌঁছে দেয় এবং পথভ্রষ্টতা থেকে রক্ষা করে।"
      ),
      Ayah(
        1, 7,
        "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
        "তাদের পথ, যাদের আপনি পুরস্কৃত করেছেন; তাদের পথ নয় যারা ক্রোধের শিকার হয়েছে এবং যারা পথভ্রষ্ট হয়েছে। (আমিন)",
        "The path of those upon whom You have bestowed favor, not of those who have evoked [Your] anger or of those who are astray.",
        listOf(
          WordTranslation("صِرَاطَ", "Sirata", "পথ", "The path of"),
          WordTranslation("الَّذِينَ", "alladhina", "যাদের", "those who"),
          WordTranslation("أَنْعَمْتَ", "an'amta", "আপনি নিয়ামত দিয়েছেন", "You bestowed favor"),
          WordTranslation("عَلَيْهِمْ", "'alayhim", "তাদের ওপর", "upon them"),
          WordTranslation("غَيْرِ", "ghayril", "ব্যতীত / নয়", "not of"),
          WordTranslation("الْمَغْضُوبِ", "maghdubi", "ক্রোধপ্রাপ্ত", "evoked anger"),
          WordTranslation("وَلَا الضَّالِّينَ", "wa lad-dallin", "এবং পথভ্রষ্ট নয়", "nor those astray")
        ),
        "তাফসীর: নিয়ামতপ্রাপ্তরা হলেন নবী, সিদ্দিক, শহীদ ও সালেহীন বান্দাগণ। আর ক্রোধপ্রাপ্ত ও পথভ্রষ্টদের পরিণতি থেকে বেঁচে থাকার জন্য এ দোয়ায় মিনতি করা হয়েছে।"
      )
    ),

    // Surah Al-Ikhlas (112)
    112 to listOf(
      Ayah(
        112, 1,
        "قُلْ هُوَ اللَّهُ أَحَدٌ",
        "বলুন, তিনিই আল্লাহ, একক ও অদ্বিতীয়।",
        "Say, \"He is Allah, [who is] One,",
        listOf(
          WordTranslation("قُلْ", "Qul", "বলুন", "Say"),
          WordTranslation("هُوَ", "Huwa", "তিনি", "He is"),
          WordTranslation("اللَّهُ", "Allahu", "আল্লাহ", "Allah"),
          WordTranslation("أَحَدٌ", "Ahad", "একক ও অদ্বিতীয়", "One")
        ),
        "তাফসীর: তাওহীদের সর্বোচ্চ ঘোষণা। আল্লাহর সত্ত্বা ও গুণে কোনো সমকক্ষ নেই।"
      ),
      Ayah(
        112, 2,
        "اللَّهُ الصَّمَدُ",
        "আল্লাহ কারো মুখাপেক্ষী নন, সবাই তাঁর মুখাপেক্ষী।",
        "Allah, the Eternal Refuge.",
        listOf(
          WordTranslation("اللَّهُ", "Allahu", "আল্লাহ", "Allah"),
          WordTranslation("الصَّمَدُ", "As-Samad", "অমুখাপেক্ষী / চরম আশ্রয়", "the Eternal Refuge")
        ),
        "তাফসীর: 'আস-সামাদ' শব্দের অর্থ সমগ্র সৃষ্টি যার মুখাপেক্ষী কিন্তু তিনি কোনো কিছুর মুখাপেক্ষী নন।"
      ),
      Ayah(
        112, 3,
        "لَمْ يَلِدْ وَلَمْ يُولَدْ",
        "তাঁর কোনো সন্তান নেই এবং তিনিও কারো সন্তান নন।",
        "He neither begets nor is born,",
        listOf(
          WordTranslation("لَمْ يَلِدْ", "Lam yalid", "তিনি জন্ম দেননি", "He begets not"),
          WordTranslation("وَلَمْ يُولَدْ", "wa lam yulad", "এবং জন্মগ্রহণ করেননি", "nor is born")
        ),
        "তাফসীর: আল্লাহ পিতা ও পুত্রের যাবতীয় মানবিক সম্পর্ক ও অক্ষমতা থেকে সম্পূর্ণ পবিত্র ও ঊর্ধ্বে।"
      ),
      Ayah(
        112, 4,
        "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",
        "এবং তাঁর সমতুল্য কেউই নেই।",
        "Nor is there to Him any equivalent.\"",
        listOf(
          WordTranslation("وَلَمْ يَكُن", "wa lam yakun", "এবং নেই", "and there is not"),
          WordTranslation("لَّهُ", "lahu", "তাঁর", "to Him"),
          WordTranslation("كُفُوًا", "kufuwan", "সমতুল্য", "equivalent"),
          WordTranslation("أَحَدٌ", "ahad", "কেউ", "anyone")
        ),
        "তাফসীর: সৃষ্টিজগতের কোনো রূপ, শক্তি বা উপমার সাথে আল্লাহর তুলনা অসম্ভব।"
      )
    ),

    // Surah Al-Falaq (113)
    113 to listOf(
      Ayah(113, 1, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "বলুন, আমি আশ্রয় প্রার্থনা করছি উষার প্রতিপালকের কাছে,", "Say, \"I seek refuge in the Lord of daybreak", emptyList(), "তাফসীর: সকল অন্ধকারের পর আলো দানকারী রবের কাছে আশ্রয়।"),
      Ayah(113, 2, "مِن شَرِّ مَا خَلَقَ", "তিনি যা সৃষ্টি করেছেন তার অনিষ্ট থেকে,", "From the evil of that which He created", emptyList(), "তাফসীর: সকল ক্ষতিকর সৃষ্টি ও অশুভ প্রভাব থেকে আশ্রয় প্রার্থনা।"),
      Ayah(113, 3, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", "এবং অন্ধকার রাত্রির অনিষ্ট থেকে যখন তা নিবিড় হয়,", "And from the evil of darkness when it settles", emptyList(), "তাফসীর: রাতের আঁধারে ছড়িয়ে পড়া বিপদাপদ থেকে পানাহ।"),
      Ayah(113, 4, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "এবং গিরায় ফুঁকদানকারী জাদুকারিণীদের অনিষ্ট থেকে,", "And from the evil of the blowers in knots", emptyList(), "তাফসীর: জাদু ও কুফরি কাজের অশুভ থেকে হেফাজত।"),
      Ayah(113, 5, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", "এবং হিংসুকের অনিষ্ট থেকে যখন সে হিংসা করে।", "And from the evil of an envier when he envies.\"", emptyList(), "তাফসীর: কুদৃষ্টি ও মারাত্মক অন্তর্জ্বালা হিংসা থেকে নিরাপত্তা।")
    ),

    // Surah An-Nas (114)
    114 to listOf(
      Ayah(114, 1, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "বলুন, আমি আশ্রয় চাচ্ছি মানুষের প্রতিপালকের কাছে,", "Say, \"I seek refuge in the Lord of mankind,", emptyList(), "তাফসীর: মানুষের সৃষ্টিকর্তা ও লালনপালনকারী একমাত্র আল্লাহর আশ্রয়।"),
      Ayah(114, 2, "مَلِكِ النَّاسِ", "মানুষের অধিপতির কাছে,", "The Sovereign of mankind.", emptyList(), "তাফসীর: মানুষের প্রকৃত রাজাধিরাজ যিনি।"),
      Ayah(114, 3, "إِلَٰهِ النَّاسِ", "মানুষের উপাস্যের কাছে,", "The God of mankind,", emptyList(), "তাফসীর: মানুষের একমাত্র সত্যিকারের মাবুদ।"),
      Ayah(114, 4, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "গোপন কুমন্ত্রণাদাতার অনিষ্ট থেকে যে বার বার পিছু হটে,", "From the evil of the retreating whisperer -", emptyList(), "তাফসীর: শয়তান যে অন্তরে কুবুদ্ধি দেয় ও আল্লাহর স্মরণে পলায়ন করে।"),
      Ayah(114, 5, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "যে মানুষের অন্তরে কুমন্ত্রণা দেয়,", "Who whispers [evil] into the breasts of mankind -", emptyList(), "তাফসীর: অন্তরের গোপন চিন্তা ও বিভ্রান্তি সৃষ্টির বিরুদ্ধে দুর্গ।"),
      Ayah(114, 6, "مِنَ الْجِنَّةِ وَالنَّاسِ", "জ্বিনদের মধ্য থেকে অথবা মানুষের মধ্য থেকে।", "From among the jinn and mankind.\"", emptyList(), "তাফসীর: দৃশ্য ও অদৃশ্য সব ধরনের কুমন্ত্রণাদাতার থেকে আল্লাহর আশ্রয় প্রার্থনা।")
    ),

    // Surah Al-Kawthar (108)
    108 to listOf(
      Ayah(108, 1, "إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ", "নিশ্চয়ই আমি আপনাকে কাউসার (প্রচুর কল্যাণ ও সুমিষ্ট ঝরনা) দান করেছি।", "Indeed, We have granted you, [O Muhammad], al-Kawthar.", emptyList(), "তাফসীর: জান্নাতের বিখ্যাত কাউসার নহর এবং রাসুলুল্লাহ ﷺ এর বিপুল মর্যাদা।"),
      Ayah(108, 2, "فَصَلِّ لِرَبِّكَ وَانْحَرْ", "অতএব আপনার পালনকর্তার উদ্দেশ্যে নামাজ পড়ুন এবং কোরবানি করুন।", "So pray to your Lord and sacrifice [to Him alone].", emptyList(), "তাফসীর: একনিষ্ঠভাবে নামাজ ও কোরবানির মাধ্যমে আল্লাহর শুকরিয়া আদায়।"),
      Ayah(108, 3, "إِنَّ شَانِئَكَ هُوَ الْأَبْتَرُ", "নিশ্চয়ই আপনার শত্রুরাই নির্বংশ ও লেজকাটা।", "Indeed, your enemy is the one cut off.", emptyList(), "তাফসীর: রাসুলুল্লাহ ﷺ এর স্মৃতি ও উম্মত চিরকাল উজ্জ্বল থাকবে, বিরোধীরাই ধ্বংসপ্রাপ্ত।")
    ),

    // Surah Al-Asr (103)
    103 to listOf(
      Ayah(103, 1, "وَالْعَصْرِ", "সময়ের শপথ,", "By time,", emptyList(), "তাফসীর: মানব জীবনের প্রতিটি মুহূর্ত অত্যন্ত মূল্যবান আমানত।"),
      Ayah(103, 2, "إِنَّ الْإِنسَانَ لَفِي خُسْرٍ", "নিশ্চয়ই সমস্ত মানুষ চরম ক্ষতির মধ্যে নিমজ্জিত,", "Indeed, mankind is in loss,", emptyList(), "তাফসীর: ঈমানহীন জীবন ব্যর্থতায় পর্যবসিত।"),
      Ayah(103, 3, "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ", "তারা ছাড়া, যারা ঈমান এনেছে, সৎকাজ করেছে এবং পরস্পরকে সত্যের উপদেশ দিয়েছে ও ধৈর্যের উপদেশ দিয়েছে।", "Except for those who have believed and done righteous deeds and advised each other to truth and advised each other to patience.", emptyList(), "তাফসীর: সফলতার চারটি অপরিহার্য ভিত্তি: ঈমান, নেক আমল, দাওয়াত ও সবর।")
    ),

    // Surah Ya-Sin Highlights (36)
    36 to listOf(
      Ayah(36, 1, "يس", "ইয়া-সীন।", "Ya-Sin.", emptyList(), "তাফসীর: হুরুফে মুকাত্তাআত, যার আসল মর্ম আল্লাহই ভালো জানেন।"),
      Ayah(36, 2, "وَالْقُرْآنِ الْحَكِيمِ", "প্রজ্ঞাময় কুরআনের শপথ,", "By the wise Qur'an.", emptyList(), "তাফসীর: কুরআন সত্য, বিজ্ঞান ও হেদায়েতে পরিপূর্ণ প্রজ্ঞাময় গ্রন্থ।"),
      Ayah(36, 3, "إِنَّكَ لَمِنَ الْمُرْسَلِينَ", "নিশ্চয়ই আপনি প্রেরিত রাসুলদের অন্তর্ভুক্ত,", "Indeed you, [O Muhammad], are from among the messengers,", emptyList(), "তাফসীর: রাসুলুল্লাহ ﷺ এর নবুওয়াতের দৃঢ় সাক্ষ্য।"),
      Ayah(36, 4, "عَلَىٰ صِرَاطٍ مُّسْتَقِيمٍ", "সরল-সঠিক পথের ওপর প্রতিষ্ঠিত।", "On a straight path.", emptyList(), "তাফসীর: ইসলাম একমাত্র ভারসাম্যতা ও নাজাতের পথ।"),
      Ayah(36, 5, "تَنزِيلَ الْعَزِيزِ الرَّحِيمِ", "এ কুরআন মহা পরাক্রমশালী ও পরম দয়ালু আল্লাহর পক্ষ থেকে অবতীর্ণ।", "[This is] a revelation of the Exalted in Might, the Merciful.", emptyList(), "তাফসীর: আল্লাহর অসীম রহমত মানুষের হিদায়েতের জন্য কুরআন দান করেছেন।")
    ),

    // Surah Ar-Rahman Highlights (55)
    55 to listOf(
      Ayah(55, 1, "الرَّحْمَٰنُ", "পরম দয়াময় আল্লাহ,", "The Most Merciful", emptyList(), "তাফসীর: আল্লাহর রহমত সর্বব্যাপী।"),
      Ayah(55, 2, "عَلَّمَ الْقُرْآنَ", "তিনিই কুরআন শিক্ষা দিয়েছেন,", "Taught the Qur'an,", emptyList(), "তাফসীর: মানুষের জন্য সর্বশ্রেষ্ঠ অনুগ্রহ হলো কুরআন শিক্ষা।"),
      Ayah(55, 3, "خَلَقَ الْإِنسَانَ", "তিনি মানুষ সৃষ্টি করেছেন,", "Created man,", emptyList(), "তাফসীর: মাটির উপাদান থেকে নিখুঁত অবয়বে মানব সৃষ্টি।"),
      Ayah(55, 4, "عَلَّمَهُ الْبَيَانَ", "তাঁকে ভাব প্রকাশ ও কথা বলার ক্ষমতা শিখিয়েছেন।", "[And] taught him eloquence.", emptyList(), "তাফসীর: বাকশক্তি ও ভাব প্রকাশের অপরূপ নেয়ামত।"),
      Ayah(55, 13, "فَبِأَيِّ آلَاءِ رَبِّكُمَا تُكَذِّبَانِ", "অতএব, তোমরা তোমাদের পালনকর্তার কোন্ কোন্ অনুগ্রহকে অস্বীকার করবে?", "So which of the favors of your Lord would you deny?", emptyList(), "তাফসীর: মানুষ ও জ্বিন জাতির প্রতি আল্লাহর অসীম উপহারসমূহ স্মরণের আহ্বান।")
    ),

    // Surah Al-Mulk Highlights (67)
    67 to listOf(
      Ayah(67, 1, "تَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ", "বরকতময় তিনি, যাঁর হাতে সমস্ত রাজত্ব এবং তিনি সবকিছুর ওপর সর্বশক্তিমান।", "Blessed is He in whose hand is dominion, and He is over all things competent -", emptyList(), "তাফসীর: সমগ্র মহাবিশ্বের সার্বভৌম কর্তৃত্ব আল্লাহর হাতে।"),
      Ayah(67, 2, "الَّذِي خَلَقَ الْمَوْتَ وَالْحَيَاةَ لِيَبْلُوَكُمْ أَيُّكُمْ أَحْسَنُ عَمَلًا وَهُوَ الْعَزِيزُ الْغَفُورُ", "যিনি সৃষ্টি করেছেন মৃত্যু ও জীবন, যাতে তোমাদের পরীক্ষা করেন যে কে তোমাদের মধ্যে আমলের দিক থেকে সর্বোত্তম। আর তিনি পরাক্রমশালী, ক্ষমাশীল।", "[He] who created death and life to test you [as to] which of you is best in deed - and He is the Exalted in Might, the Forgiving -", emptyList(), "তাফসীর: পার্থিব জীবনের আসল উদ্দেশ্য উত্তম কর্মের মাধ্যমে আল্লাহর সন্তুষ্টি অর্জন।")
    )
  )

  // Today's Ayah of the day for Home Dashboard
  val dailyAyah: Ayah = Ayah(
    surahNumber = 2,
    ayahNumber = 186,
    arabicText = "وَإِذَا سَأَلَكَ عِبَادِي عَنِّي فَإِنِّي قَرِيبٌ ۖ أُجِيبُ دَعْوَةَ الدَّاعِ إِذَا دَعَانِ",
    bengaliTranslation = "আর আমার বান্দারা যখন আপনার কাছে আমার সম্পর্কে জিজ্ঞেস করে, তখন নিশ্চয়ই আমি অতি নিকটে। আহ্বানকারী যখনই আমাকে ডাকে, আমি তার ডাকে সাড়া দেই।",
    englishTranslation = "And when My servants ask you, [O Muhammad], concerning Me - indeed I am near. I respond to the invocation of the supplicant when he calls upon Me.",
    wordByWord = listOf(
      WordTranslation("وَإِذَا", "Wa idha", "এবং যখন", "And when"),
      WordTranslation("سَأَلَكَ", "sa'alaka", "আপনাকে জিজ্ঞেস করে", "ask you"),
      WordTranslation("عِبَادِي", "'ibadi", "আমার বান্দাগণ", "My servants"),
      WordTranslation("عَنِّي", "'anni", "আমার সম্পর্কে", "concerning Me"),
      WordTranslation("فَإِنِّي", "fa inni", "তখন নিশ্চয়ই আমি", "indeed I am"),
      WordTranslation("قَرِيبٌ", "qarib", "অতি নিকটে", "near"),
      WordTranslation("أُجِيبُ", "ujeebu", "আমি সাড়া দেই", "I respond"),
      WordTranslation("دَعْوَةَ", "da'wata", "ডাকে / দোয়ায়", "to invocation")
    ),
    tafsirSummary = "সূরা আল-বাক্বারাহ: ১৮৬ - আল্লাহ তাআলা বান্দার শাহরগের চেয়েও কাছে। গভীর অন্তরের প্রতিটি আরজি তিনি শোনেন ও যথাসময়ে সর্বোত্তম সাড়া দেন।"
  )

  // Ayatul Kursi - The Greatest Verse
  val ayatulKursi: Ayah = Ayah(
    surahNumber = 2,
    ayahNumber = 255,
    arabicText = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ مَن ذَا الَّذِي يَشْفَعُ عِندَهُ إِلَّا بِإِذْنِهِ ۚ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ ۖ وَلَا يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ ۖ وَلَا يَئُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ",
    bengaliTranslation = "আল্লাহ! তিনি ছাড়া কোনো সত্য উপাস্য নেই, তিনি চিরঞ্জীব, সর্বসত্তার ধারক। তন্দ্রা বা নিদ্রা তাঁকে স্পর্শ করে না। আকাশমণ্ডলী ও পৃথিবীতে যা কিছু আছে সবই তাঁর। কে আছে যে তাঁর অনুমতি ব্যতীত তাঁর কাছে সুপারিশ করতে পারে? মানুষের সম্মুখে ও পেছনে যা কিছু আছে তা তিনি জানেন। তাঁর জ্ঞানের কোনো অংশই মানুষ আয়ত্ত করতে পারে না, কেবল তিনি যা ইচ্ছা করেন তা ছাড়া। তাঁর কুরসী সমস্ত আকাশ ও পৃথিবীকে পরিবেষ্টন করে আছে, এবং এ দুয়ের সংরক্ষণ তাঁকে বিন্দুমাত্র ক্লান্ত করে না। তিনি সর্বোচ্চ, মহান।",
    englishTranslation = "Allah - there is no deity except Him, the Ever-Living, the Sustainer of all existence. Neither drowsiness overtakes Him nor sleep. To Him belongs whatever is in the heavens and whatever is on the earth. Who is it that can intercede with Him except by His permission? He knows what is before them and what will be after them, and they encompass not a thing of His knowledge except for what He wills. His Kursi extends over the heavens and the earth, and their preservation tires Him not. And He is the Most High, the Most Great.",
    wordByWord = emptyList(),
    tafsirSummary = "আয়াতুল কুরসি: কুরআনের সর্বশ্রেষ্ঠ আয়াত। এতে আল্লাহর একত্ববাদ, চিরঞ্জীব সত্ত্বা, পূর্ণ সার্বভৌমত্ব ও অনন্ত জ্ঞানের বিশদ বর্ণনা রয়েছে। প্রতিটি ফরজ নামাজের পর পাঠে জান্নাত প্রাপ্তির ঘোষণা রয়েছে।"
  )
}
