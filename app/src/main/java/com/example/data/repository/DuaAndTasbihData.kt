package com.example.data.repository

import com.example.data.model.AsmaulHusna
import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem
import com.example.data.model.TasbihPreset

object DuaAndTasbihData {

  val categories: List<DuaCategory> = listOf(
    DuaCategory("morning_evening", "সকাল ও সন্ধ্যার আজকার", "Morning & Evening", "wb_sunny", 6),
    DuaCategory("prayer", "নামাজের গুরুত্বপূর্ণ দোয়া", "Salah Duas", "mosque", 6),
    DuaCategory("sleep", "ঘুমানোর পূর্বে ও পরের দোয়া", "Sleep & Waking", "bedtime", 4),
    DuaCategory("daily", "প্রতিদিনের প্রয়োজনীয় দোয়া", "Daily Life", "home", 6),
    DuaCategory("forgiveness", "মাগফিরাত ও তওবার দোয়া", "Forgiveness & Istighfar", "favorite", 5),
    DuaCategory("protection", "বিপদমুক্তি ও রোগমুক্তির দোয়া", "Protection & Health", "shield", 5)
  )

  val duas: List<DuaItem> = listOf(
    // Morning / Evening
    DuaItem(
      id = "sayyidul_istighfar",
      categoryId = "morning_evening",
      titleBn = "সাইয়্যিদুল ইস্তিগফার (তওবার শ্রেষ্ঠ দোয়া)",
      titleEn = "Sayyidul Istighfar (Chief of Forgiveness)",
      arabicText = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَٰهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَىٰ عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
      transliterationBn = "আল্লাহুম্মা আনতা রব্বী, লা ইলাহা ইল্লা আনতা, খালাকতানী ওয়া আনা আবদুকা, ওয়া আনা আ'লা আহদিকা ওয়া ওয়াদিকা মাসতাত্বা'তু, আ'ঊযুবিকা মিন শাররি মা সানা'তু, আবূউ লাকা বিনি'মাতিকা আলাইয়্যা, ওয়া আবূউ বিযানবী, ফাগফির লী, ফাইন্নাহু লা ইয়াগফিরুয যুনূবা ইল্লা আনতা।",
      bengaliMeaning = "হে আল্লাহ! আপনিই আমার রব, আপনি ছাড়া কোনো সত্য উপাস্য নেই। আপনি আমাকে সৃষ্টি করেছেন এবং আমি আপনার দাস। আমি আমার সাধ্যমতো আপনার অঙ্গীকার ও প্রতিশ্রুতির ওপর অবিচল আছি। আমি যা করেছি তার অনিষ্ট থেকে আপনার কাছে আশ্রয় চাই। আমার ওপর আপনার যে নেয়ামত রয়েছে তা স্বীকার করছি এবং আমার গুনাহের স্বীকৃতি দিচ্ছি। অতএব আমাকে ক্ষমা করুন; কারণ আপনি ছাড়া গুনাহ ক্ষমা করার আর কেউ নেই।",
      englishMeaning = "O Allah, You are my Lord, there is no god but You. You created me and I am Your servant, and I remain upon Your covenant and Your promise as much as I can. I seek refuge in You from the evil of what I have done. I acknowledge before You Your blessings upon me, and I acknowledge my sins, so forgive me, for none can forgive sins except You.",
      reference = "সহীহ বুখারী: ৬৩০৬",
      benefitBn = "যে ব্যক্তি দৃঢ় বিশ্বাসের সাথে দিনে এই দোয়া পড়বে এবং সন্ধ্যায় মারা যাবে সে জান্নাতবাসী হবে; আর যে সন্ধ্যায় পড়বে এবং সকালে মারা যাবে সেও জান্নাতবাসী হবে।",
      repeatCount = 1
    ),
    DuaItem(
      id = "bismillahillazi",
      categoryId = "morning_evening",
      titleBn = "বিপদ-আপদ থেকে সুরক্ষার সকাল-সন্ধ্যার দোয়া",
      titleEn = "Protection Against All Harms",
      arabicText = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
      transliterationBn = "বিসমিল্লাহিল্লাজি লা ইয়াদুররু মা'আসমিহি শাইয়ুন ফিল আরদ্বি ওয়ালা ফিস সামা-ই, ওয়াহুওয়াস সামীউল আলীম।",
      bengaliMeaning = "আল্লাহর নামে শুরু করছি, যাঁর নামের বরকতে আসমান ও জমিনের কোনো কিছুই অনিষ্ট করতে পারে না; আর তিনি সর্বশ্রোতা, সর্বজ্ঞানী।",
      englishMeaning = "In the name of Allah, with whose name nothing on earth or in the heavens can cause harm, and He is the All-Hearing, the All-Knowing.",
      reference = "সুনান আবু দাউদ: ৫০৮৮, জামে আত-তিরমিজী: ৩৩৮৮",
      benefitBn = "সকাল ও সন্ধ্যায় তিনবার পাঠ করলে কোনো বিষধর প্রাণী, জাদু বা হঠাৎ বিপদ ক্ষতি করতে পারে না।",
      repeatCount = 3
    ),
    DuaItem(
      id = "radheetu_billah",
      categoryId = "morning_evening",
      titleBn = "আল্লাহর সন্তুষ্টি লাভের দোয়া",
      titleEn = "Pleased with Allah as Lord",
      arabicText = "رَضِيتُ بِاللَّهِ رَبًّا، وَبِالْإِسْلَامِ دِينًا، وَبِمُحَمَّدٍ صَلَّى اللَّهُ عَلَيْهِ وَسَلَّمَ نَبِيًّا",
      transliterationBn = "রাদীতু বিল্লাহি রব্বান, ওয়া বিল ইসলামি দ্বীনান, ওয়া বি মুহাম্মাদিন সাল্লাল্লাহু আলাইহি ওয়া সাল্লামা নাবিয়্যান।",
      bengaliMeaning = "আমি আল্লাহকে প্রতিপালক হিসেবে, ইসলামকে দ্বীন হিসেবে এবং মুহাম্মদ ﷺ-কে নবী হিসেবে পেয়ে সন্তুষ্ট চিত্তে গ্রহণ করেছি।",
      englishMeaning = "I am pleased with Allah as my Lord, with Islam as my religion, and with Muhammad (peace be upon him) as my Prophet.",
      reference = "সহীহ মুসলিম: ৩৪, তিরমিজী: ৩৩৮৯",
      benefitBn = "কিয়ামতের দিন আল্লাহ তাআলা এই পাঠকারী বান্দাকে সন্তুষ্ট করার দায়িত্ব নিজের ওপর গ্রহণ করেন।",
      repeatCount = 3
    ),

    // Prayer Duas
    DuaItem(
      id = "dua_qunut",
      categoryId = "prayer",
      titleBn = "দোয়া কুনুত (বিতর নামাজের দোয়া)",
      titleEn = "Dua Qunut (Witr Prayer)",
      arabicText = "اللَّهُمَّ اهْدِنِي فِيمَنْ هَدَيْتَ، وَعَافِنِي فِيمَنْ عَافَيْتَ، وَتَوَلَّنِي فِيمَنْ تَوَلَّيْتَ، وَبَارِكْ لِي فِيمَا أَعْطَيْتَ، وَقِنِي شَرَّ مَا قَضَيْتَ، فَإِنَّكَ تَقْضِي وَلَا يُقْضَىٰ عَلَيْكَ، وَإِنَّهُ لَا يَذِلُّ مَنْ وَالَيْتَ، وَلَا يَعِزُّ مَنْ عَادَيْتَ، تَبَارَكْتَ رَبَّنَا وَتَعَالَيْتَ",
      transliterationBn = "আল্লাহুম্মাহদিনী ফীমান হাদায়তা, ওয়া আ'ফিনী ফীমান আফায়তা, ওয়া তাওয়াল্লানী ফীমান তাওয়াল্লায়তা, ওয়া বারিক লী ফীমা আ'ত্বায়তা, ওয়া ক্বিনী শাররা মা ক্বাদায়তা, ফাইন্নাকা তাক্বদ্বী ওয়ালা ইয়ুক্বদ্বা আলাইকা, ওয়া ইন্নাহু লা ইয়াযিল্লু মাও ওয়ালায়তা, ওয়ালা ইয়ায়িজ্জু মান আদায়তা, তাবারাকতা রব্বানা ওয়া তাআ'লায়তা।",
      bengaliMeaning = "হে আল্লাহ! আপনি যাদের হেদায়েত দান করেছেন আমাকে তাদের অন্তর্ভুক্ত করুন, যাদের সুস্থতা দান করেছেন আমাকে তাদের সাথে সুস্থ রাখুন, যাদের অভিভাবকত্ব গ্রহণ করেছেন আমারও অভিভাবকত্ব গ্রহণ করুন, যা দান করেছেন তাতে বরকত দিন, আর যা ফয়সালা করেছেন তার অনিষ্ট থেকে আমাকে রক্ষা করুন। কারণ আপনিই ফয়সালাকারী, আপনার ওপর কোনো ফয়সালা চলে না। নিশ্চয়ই আপনি যার অভিভাবক সে কখনো লাঞ্ছিত হয় না, আর আপনি যার সাথে শত্রুতা করেন সে কখনো সম্মানিত হতে পারে না। আপনি বরকতময় হে আমাদের রব এবং আপনি সর্বোচ্চ মর্যাদাবান।",
      englishMeaning = "O Allah, guide me among those whom You have guided, grant me well-being among those whom You have granted well-being, take me under Your care among those whom You have taken under Your care, bless what You have given me, and save me from the evil of what You have decreed. For indeed, You decree and none can decree against You. Indeed, he whom You ally with is never humiliated, and he whom You oppose is never honored. Blessed are You, our Lord, and Exalted.",
      reference = "সুনান আবু দাউদ: ১৪২৫, তিরমিজী: ৪৬৪",
      benefitBn = "বিতরের শেষ রাকাতে রুকুর পূর্বে বা পরে পাঠ করা সুন্নাতসম্মত মহিমান্বিত দোয়া।",
      repeatCount = 1
    ),
    DuaItem(
      id = "rabbana_atina",
      categoryId = "prayer",
      titleBn = "কুরআনের সর্বশ্রেষ্ঠ দোয়া (দুনিয়া ও আখিরাতের কল্যাণ)",
      titleEn = "Supplication for Good in Both Worlds",
      arabicText = "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ",
      transliterationBn = "রব্বানা আতিনা ফিদ দুনইয়া হাসানাতাও ওয়া ফিল আখিরাতি হাসানাতাও ওয়া ক্বিনা আযাবান নার।",
      bengaliMeaning = "হে আমাদের পালনকর্তা! আমাদের ইহকালে কল্যাণ দান করুন, পরকালেও কল্যাণ দান করুন এবং জাহান্নামের আজাব থেকে আমাদের রক্ষা করুন।",
      englishMeaning = "Our Lord, give us in this world that which is good and in the Hereafter that which is good and protect us from the punishment of the Fire.",
      reference = "সূরা বাক্বারাহ: ২০১, সহীহ বুখারী: ৬৩৮৯",
      benefitBn = "রাসুলুল্লাহ ﷺ এই দোয়াটি সবচেয়ে বেশি পাঠ করতেন। তাওয়াফে এবং যেকোনো দোয়ার শেষে পাঠ করা অত্যন্ত বরকতময়।",
      repeatCount = 1
    ),

    // Sleep
    DuaItem(
      id = "dua_sleeping",
      categoryId = "sleep",
      titleBn = "ঘুমানোর পূর্বে পাঠ করার দোয়া",
      titleEn = "Before Sleeping",
      arabicText = "اللَّهُمَّ بِاسْمِكَ أَمُوتُ وَأَحْيَا",
      transliterationBn = "আল্লাহুম্মা বিসমিকা আমূতু ওয়া আহ্ইয়া।",
      bengaliMeaning = "হে আল্লাহ! আপনারই নামে আমি মারা যাই (ঘুমাই) এবং আবার জীবিত হই (জাগ্রত হই)।",
      englishMeaning = "O Allah, in Your name I die and I live.",
      reference = "সহীহ বুখারী: ৬৩১২",
      benefitBn = "ঘুমের মাঝে আল্লাহর নিরাপত্তা লাভ এবং জীবন-মৃত্যুর স্মরণে অন্তর সজাগ রাখা।",
      repeatCount = 1
    ),
    DuaItem(
      id = "dua_waking",
      categoryId = "sleep",
      titleBn = "ঘুম থেকে জেগে ওঠার দোয়া",
      titleEn = "Upon Waking Up",
      arabicText = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
      transliterationBn = "আলহামদু লিল্লাহিল্লাজি আহ্ইয়ানা বা'দা মা আমাতানা ওয়া ইলাইহিন নুশূর।",
      bengaliMeaning = "সমস্ত প্রশংসা আল্লাহর জন্য, যিনি আমাদের মৃত্যুর পর (ঘুমের পর) পুনরায় জীবিত করলেন এবং তাঁরই দিকে সকলের পুনরুত্থান হবে।",
      englishMeaning = "All praise is due to Allah who gave us life after giving us death, and unto Him is the resurrection.",
      reference = "সহীহ বুখারী: ৬৩১৪",
      benefitBn = "দিনের শুরুতে সর্বপ্রথম আল্লাহর প্রতি গভীর কৃতজ্ঞতা প্রকাশ।",
      repeatCount = 1
    ),

    // Protection & Hardship
    DuaItem(
      id = "dua_yunus",
      categoryId = "protection",
      titleBn = "দোয়া ইউনুস (বিপদ ও কঠিন সংকটে পাঠের দোয়া)",
      titleEn = "Dua Yunus (Supplication of Jonah)",
      arabicText = "لَّا إِلَٰهَ إِلَّا أَنتَ سُبْحَانَكَ إِنِّي كُنتُ مِنَ الظَّالِمِينَ",
      transliterationBn = "লা ইলাহা ইল্লা আনতা সুবহানাকা ইন্নি কুনতু মিনায যালিমীন।",
      bengaliMeaning = "আপনি ছাড়া কোনো সত্য উপাস্য নেই, আপনি মহাপবিত্র! নিশ্চয়ই আমি নিজের ওপর জুলুমকারীদের অন্তর্ভুক্ত হয়ে গেছি।",
      englishMeaning = "There is no deity except You; exalted are You. Indeed, I have been of the wrongdoers.",
      reference = "সূরা আম্বিয়া: ৮৭, জামে আত-তিরমিজী: ৩৫০৫",
      benefitBn = "মাছের পেটে নবী ইউনুস (আ.) এই দোয়ার মাধ্যমে মুক্তি পেয়েছিলেন। যেকোনো কঠিন বিপদ ও রোগব্যাধিতে পাঠ করলে আল্লাহ তা দূর করে দেন।",
      repeatCount = 1
    ),
    DuaItem(
      id = "dua_anxiety",
      categoryId = "protection",
      titleBn = "দুশ্চিন্তা, ঋণ ও অলসতা থেকে মুক্তির দোয়া",
      titleEn = "Protection from Worry and Debt",
      arabicText = "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْهَمِّ وَالْحَزَنِ، وَالْعَجْزِ وَالْكَسَلِ، وَالْبُخْلِ وَالْجُبْنِ، وَضَلَعِ الدَّيْنِ وَغَلَبَةِ الرِّجَالِ",
      transliterationBn = "আল্লাহুম্মা ইন্নি আ'ঊযুবিকা মিনাল হাম্মি ওয়াল হাযানি, ওয়াল আজযি ওয়াল কাসালি, ওয়াল বুখলি ওয়াল জুবনি, ওয়া দ্বালা'ইদ দাইনি ওয়া গালাবাতির রিজাল।",
      bengaliMeaning = "হে আল্লাহ! আমি আপনার কাছে আশ্রয় চাই দুশ্চিন্তা ও শোক থেকে, অক্ষমতা ও অলসতা থেকে, কৃপণতা ও কাপুরুষতা থেকে, ঋণের বোঝা এবং মানুষের জুলুম ও প্রাধান্য বিস্তার থেকে।",
      englishMeaning = "O Allah, I seek refuge in You from anxiety and sorrow, from weakness and laziness, from miserliness and cowardice, from being overwhelmed by debt and from the oppression of men.",
      reference = "সহীহ বুখারী: ২৮৯৩",
      benefitBn = "মানসিক শান্তি, ঋণের বোঝা থেকে মুক্তি এবং কর্মোদ্যম বৃদ্ধি পায়।",
      repeatCount = 1
    )
  )

  val tasbihPresets: List<TasbihPreset> = listOf(
    TasbihPreset("subhanallah", "سُبْحَانَ اللَّهِ", "সুবহানাল্লাহ", "আল্লাহ মহাপবিত্র", 33, "প্রতি তাসবীহে জান্নাতে একটি করে গাছ রোপণ করা হয়।"),
    TasbihPreset("alhamdulillah", "الْحَمْدُ لِلَّهِ", "আলহামদুলিল্লাহ", "সমস্ত প্রশংসা আল্লাহর", 33, "মীযানের পাল্লা সবচেয়ে ভারীকারী প্রিয় বাক্য।"),
    TasbihPreset("allahuakbar", "اللَّهُ أَكْبَرُ", "আল্লাহু আকবার", "আল্লাহ সর্বশ্রেষ্ঠ", 34, "আসমান ও জমিনের শূন্যস্থান সওয়াবে পরিপূর্ণ করে।"),
    TasbihPreset("lailahaillallah", "لَا إِلَٰهَ إِلَّا اللَّهُ", "লা ইলাহা ইল্লাল্লাহ", "আল্লাহ ছাড়া কোনো উপাস্য নেই", 100, "সর্বোত্তম জিকির ও ঈমানের শ্রেষ্ঠ বাক্য।"),
    TasbihPreset("astaghfirullah", "أَسْتَغْفِرُ اللَّهَ", "আস্তাগফিরুল্লাহ", "আমি আল্লাহর কাছে ক্ষমা চাই", 100, "রিজিকে বরকত আনে ও সকল দুশ্চিন্তা দূর করে।"),
    TasbihPreset("durood", "اللَّهُمَّ صَلِّ عَلَىٰ مُحَمَّدٍ", "আল্লাহুম্মা সাল্লি আলা মুহাম্মদ", "হে আল্লাহ! মুহাম্মদ ﷺ এর ওপর দরূদ বর্ষণ করুন", 100, "একবার পাঠে ১০টি রহমত, ১০টি গুনাহ মাফ ও ১০টি মর্যাদা বৃদ্ধি পায়।")
  )

  val asmaulHusnaList: List<AsmaulHusna> = listOf(
    AsmaulHusna(1, "الرَّحْمَٰنُ", "আর-রাহমান (Ar-Rahman)", "পরম দয়াময়", "The Entirely Merciful", "যাঁর দয়া সকল সৃষ্টিজীবের ওপর পরিব্যাপ্ত।"),
    AsmaulHusna(2, "الرَّحِيمُ", "আর-রাহীম (Ar-Raheem)", "অতিশয় মেহেরবান", "The Especially Merciful", "যিনি পরকালে মুমিনদের ওপর বিশেষ রহমত বর্ষণ করবেন।"),
    AsmaulHusna(3, "الْمَلِكُ", "আল-মালিক (Al-Malik)", "সার্বভৌম অধিপতি", "The King, Sovereign", "সমগ্র বিশ্বজাহানের একমাত্র প্রকৃত শাসক ও সম্রাট।"),
    AsmaulHusna(4, "الْقُدُّوسُ", "আল-কুদ্দুস (Al-Quddus)", "মহাপবিত্র ও নিষ্কলুষ", "The Most Holy", "সকল দোষ-ত্রুটি, দুর্বলতা ও অসম্পূর্ণতা থেকে পবিত্র।"),
    AsmaulHusna(5, "السَّلَامُ", "আস-সালাম (As-Salam)", "শান্তিদাতা ও নিরাপত্তাদাতা", "The Source of Peace", "যিনি সৃষ্টির জন্য নিরাপত্তা ও শান্তির অবিচল উৎস।"),
    AsmaulHusna(6, "الْمُؤْمِنُ", "আল-মুমিন (Al-Mu'min)", "নিরাপত্তা ও ঈমানদানকারী", "The Granter of Security", "যিনি তাঁর বান্দাকে ভয়ভীতি থেকে নিরাপত্তা ও নির্ভরতা দেন।"),
    AsmaulHusna(7, "الْمُهَيْمِنُ", "আল-মুহাইমিন (Al-Muhaymin)", "মহাপরিবেষ্টনকারী ও রক্ষক", "The Guardian, Preserver", "যিনি সবকিছু প্রত্যক্ষ করেন এবং সবকিছু পর্যবেক্ষণ করেন।"),
    AsmaulHusna(8, "الْعَزِيزُ", "আল-আযীয (Al-Azeez)", "পরাক্রমশালী ও অপরাজেয়", "The Almighty", "যাঁর শক্তির ওপর কেউ জয়ী হতে পারে না।"),
    AsmaulHusna(9, "الْجَبَّارُ", "আল-জাব্বার (Al-Jabbar)", "সর্বশক্তিমান বাধ্যকারী", "The Compeller, Restorer", "যিনি সকল ভাঙা হৃদয় জোড়া লাগান এবং নিখুঁত শৃঙ্খলা আনেন।"),
    AsmaulHusna(10, "الْمُتَكَبِّرُ", "আল-মুতাকাব্বির (Al-Mutakabbir)", "গৌরবান্বিত ও অহংকারের অধিকারী", "The Supreme", "যিনি সকল মহিমা ও অহংকারের একমাত্র যোগ্য সত্ত্বা।"),
    AsmaulHusna(11, "الْخَالِقُ", "আল-খালিক (Al-Khaliq)", "একমাত্র স্রষ্টা", "The Creator", "যিনি শূন্য থেকে নিখুঁত পরিকল্পনায় সৃষ্টি করেছেন।"),
    AsmaulHusna(12, "الْبَارِئُ", "আল-বারী (Al-Bari)", "পরিকল্পনা রূপদানকারী", "The Evolver", "যিনি প্রতিটি বস্তুকে সুনির্দিষ্ট বৈশিষ্ট্যে রূপ দিয়েছেন।"),
    AsmaulHusna(13, "الْمُصَوِّرُ", "আল-মুসাওয়ির (Al-Musawwir)", "আকৃতিদানকারী", "The Fashioner", "যিনি প্রতিটি সৃষ্টিকে সুন্দরতম বৈচিত্র্যময় রূপ দান করেছেন।"),
    AsmaulHusna(14, "الْغَفَّارُ", "আল-গাফফার (Al-Ghaffar)", "মহা ক্ষমাশীল", "The All-Forgiving", "যিনি বান্দার বারবার করা গুনাহ গোপন ও ক্ষমা করেন।"),
    AsmaulHusna(15, "الْقَهَّارُ", "আল-কাহ্হার (Al-Qahhar)", "মহাপ্রতাপশালী দমনকারী", "The Subduer", "যাঁর নিয়ন্ত্রণে সমগ্র সৃষ্টি অনুগত ও মাথা নত করতে বাধ্য।"),
    AsmaulHusna(16, "الْوَهَّابُ", "আল-ওয়াহ্হাব (Al-Wahhab)", "মহা দাতা", "The Supreme Giver", "যিনি কোনো প্রতিদান ছাড়া অফুরন্ত উপহার বর্ষণ করেন।"),
    AsmaulHusna(17, "الرَّزَّاقُ", "আর-রায্যাক্ব (Ar-Razzaq)", "রিজিকদাতা", "The Provider", "যিনি প্রতিটি প্রাণীর প্রয়োজনীয় অন্ন ও খোরাক জোগান দেন।"),
    AsmaulHusna(18, "الْفَتَّاحُ", "আল-ফাত্তাহ (Al-Fattah)", "বিজয় ও উন্মোচনকারী", "The Opener, Judge", "যিনি বান্দার জন্য সকল রহমতের বন্ধ দুয়ার খুলে দেন।"),
    AsmaulHusna(19, "الْعَلِيمُ", "আল-আলীম (Al-Aleem)", "মহাজ্ঞানী ও সর্বজ্ঞ", "The All-Knowing", "যাঁর কাছে প্রকাশ্য ও গোপন কোনো কিছুই গোপন থাকে না।"),
    AsmaulHusna(20, "الْقَابِضُ", "আল-ক্বাবিদ (Al-Qabid)", "সংকোচনকারী", "The Constrictor", "যিনি হিকমতের সাথে রিজিক বা অন্তরের অবস্থা সীমিত করেন।"),
    AsmaulHusna(21, "الْبَاسِطُ", "আল-বাসিত (Al-Basit)", "প্রশস্তকারী", "The Reliever, Expander", "যিনি রহমত ও প্রাচুর্য উদারভাবে উন্মুক্ত ও প্রশস্ত করে দেন।"),
    AsmaulHusna(22, "الْخَافِضُ", "আল-খাফিদ (Al-Khafid)", "অবনমিতকারী", "The Abaser", "যিনি দাম্ভিক ও অহংকারীদের মর্যাদাহানি করেন।"),
    AsmaulHusna(23, "الرَّافِعُ", "আর-রাফি (Ar-Rafi)", "উন্নতকারী ও মর্যাদাদাতা", "The Exalter", "যিনি বিনয়ী ও মুমিন বান্দার মর্যাদা সমুন্নত করেন।"),
    AsmaulHusna(24, "الْمُعِزُّ", "আল-মুইয্য (Al-Mu'izz)", "সম্মানদাতা", "The Bestower of Honor", "যাঁকে ইচ্ছা তিনি দুনিয়া ও আখিরাতে চরম সম্মান দান করেন।"),
    AsmaulHusna(25, "الْمُذِلُّ", "আল-মুযিল (Al-Mudhill)", "অপমানকারী", "The Humiliator", "যিনি অহংকারী শত্রুদের লাঞ্ছিত করেন।"),
    AsmaulHusna(26, "السَّمِيعُ", "আস-সামী (As-Samee)", "সর্বশ্রোতা", "The All-Hearing", "যিনি প্রতিটি নিঃশব্দ ফিসফিস ও হৃদয়ের আকুতিও শোনেন।"),
    AsmaulHusna(27, "الْبَصِيرُ", "আল-বাসীর (Al-Baseer)", "সর্বদ্রষ্টা", "The All-Seeing", "যিনি গভীর অন্ধকারের ক্ষুদ্রতম অণুর গতিবিধিও দেখেন।"),
    AsmaulHusna(28, "الْحَكَمُ", "আল-হাকাম (Al-Hakam)", "ন্যায়বিচারক", "The Impartial Judge", "যাঁর ন্যায়বিচার কখনো কোনো পক্ষপাতিত্বের শিকার হয় না।"),
    AsmaulHusna(29, "الْعَدْلُ", "আল-আদল (Al-'Adl)", "পরম ন্যায়পরায়ণ", "The Utterly Just", "যাঁর সৃষ্টি ও বিধানে বিন্দুমাত্র অবিচার বা জুলুম নেই।"),
    AsmaulHusna(30, "اللَّطِيفُ", "আল-লাতীফ (Al-Lateef)", "সূক্ষ্মদর্শী ও অপার স্নেহশীল", "The Subtle, Kind", "যিনি অত্যন্ত সূক্ষ্ম ও অদৃশ্য উপায়ে বান্দাকে সাহায্য করেন।")
  )
}
