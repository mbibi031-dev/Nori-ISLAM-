package com.example.data.repository

import com.example.data.model.HadithBook
import com.example.data.model.HadithGrade
import com.example.data.model.HadithItem

object HadithData {

  val books: List<HadithBook> = listOf(
    HadithBook("bukhari", "সহীহ বুখারী", "Sahih al-Bukhari", "صحيح البخاري", "ইমাম বুখারী (রহ.)", "৭,৫৬৩টি হাদিস", "ইসলামের সবচেয়ে বিশুদ্ধ ও গ্রহণযোগ্য হাদিস সংকলন।"),
    HadithBook("muslim", "সহীহ মুসলিম", "Sahih Muslim", "صحيح مسلم", "ইমাম মুসলিম (রহ.)", "৭,৫০০টি হাদিস", "সহীহ বুখারীর পরেই সর্বাধিক বিশুদ্ধ হাদিস গ্রন্থ।"),
    HadithBook("tirmidhi", "জামে আত-তিরমিজী", "Jami` at-Tirmidhi", "جامع الترمذي", "ইমাম তিরমিজী (রহ.)", "৩,৯৫৬টি হাদিস", "হাদিসের বর্ণনাসূত্র ও ফিকহী বিশ্লেষণের চমৎকার সংকলন।"),
    HadithBook("abudawud", "সুনান আবু দাউদ", "Sunan Abi Dawud", "سنن أبي داود", "ইমাম আবু দাউদ (রহ.)", "৫,২৭৪টি হাদিস", "ফিকহ ও আহকামের হাদিসের জন্য সর্বাধিক প্রামাণ্য গ্রন্থ।"),
    HadithBook("nasai", "সুনান আন-নাসায়ী", "Sunan an-Nasa'i", "سنن النسائي", "ইমাম নাসায়ী (রহ.)", "৫,৭৫৮টি হাদিস", "উচ্চ সনদের মর্যাদা সম্পন্ন সুন্নাহ সংকলন।"),
    HadithBook("ibnmajah", "সুনান ইবনে মাজাহ", "Sunan Ibn Majah", "سنن ابن ماجه", "ইমাম ইবনে মাজাহ (রহ.)", "৪,৩৪১টি হাদিস", "সিহাহ সিত্তাহর ষষ্ঠ অন্যতম হাদিস গ্রন্থ।"),
    HadithBook("nawawi", "চল্লিশ হাদিস (ইমাম নববী)", "40 Hadith an-Nawawi", "الأربعون النووية", "ইমাম নববী (রহ.)", "৪২টি হাদিস", "দ্বীন ইসলামের মৌলিক বিশ্বাসের ওপর রচিত বিশ্বখ্যাত সংকলন।")
  )

  val hadiths: List<HadithItem> = listOf(
    HadithItem(
      id = "bukhari_1",
      bookId = "bukhari",
      bookNameBn = "সহীহ বুখারী",
      hadithNumber = 1,
      chapterBn = "ওহীর সূচনা অধ্যায়",
      chapterEn = "Revelation & Intentions",
      narratorBn = "উমর ইবনুল খাত্তাব (রা.)",
      narratorEn = "Umar ibn al-Khattab (RA)",
      arabicText = "إِنَّمَا الأَعْمَالُ بِالنِّيَّاتِ، وَإِنَّمَا لِكُلِّ امْرِئٍ مَا نَوَى، فَمَنْ كَانَتْ هِجْرَتُهُ إِلَى دُنْيَا يُصِيبُهَا أَوْ إِلَى امْرَأَةٍ يَنْكِحُهَا فَهِجْرَتُهُ إِلَى مَا هَاجَرَ إِلَيْهِ.",
      bengaliTranslation = "সকল কাজ নিয়তের ওপর নির্ভরশীল। আর প্রত্যেক ব্যক্তি যা নিয়ত করে কেবল তাই পায়। যার হিজরত দুনিয়ার কোনো সম্পদ অর্জন বা কোনো নারীকে বিয়ের উদ্দেশ্যে হবে, তার হিজরত সেই উদ্দেশ্যেই গণ্য হবে।",
      englishTranslation = "Actions are judged by motives, so each man will have what he intended. Thus, he whose migration was for Allah and His Messenger, his migration is for Allah and His Messenger; but he whose migration was for some worldly benefit or to take a woman in marriage, his migration be for that which he migrated.",
      grade = HadithGrade.MUTTAFAAQ_ALAYH,
      reference = "সহীহ বুখারী: ১, সহীহ মুসলিম: ১৯০৭"
    ),
    HadithItem(
      id = "bukhari_13",
      bookId = "bukhari",
      bookNameBn = "সহীহ বুখারী",
      hadithNumber = 13,
      chapterBn = "ঈমান অধ্যায়",
      chapterEn = "Belief",
      narratorBn = "আনাস (রা.)",
      narratorEn = "Anas ibn Malik (RA)",
      arabicText = "لاَ يُؤْمِنُ أَحَدُكُمْ حَتَّى يُحِبَّ لأَخِيهِ مَا يُحِبُّ لِنَفْسِهِ.",
      bengaliTranslation = "তোমাদের কেউ ততক্ষণ পর্যন্ত প্রকৃত মুমিন হতে পারবে না, যতক্ষণ না সে তার মুসলিম ভাইয়ের জন্য তাই পছন্দ করবে যা সে নিজের জন্য পছন্দ করে।",
      englishTranslation = "None of you truly believes until he wishes for his brother what he wishes for himself.",
      grade = HadithGrade.MUTTAFAAQ_ALAYH,
      reference = "সহীহ বুখারী: ১৩, সহীহ মুসলিম: ৪৫"
    ),
    HadithItem(
      id = "muslim_2564",
      bookId = "muslim",
      bookNameBn = "সহীহ মুসলিম",
      hadithNumber = 2564,
      chapterBn = "সদাচার ও অনুগ্রহ অধ্যায়",
      chapterEn = "Virtue, Good Manners",
      narratorBn = "আবু হুরায়রা (রা.)",
      narratorEn = "Abu Hurairah (RA)",
      arabicText = "إِنَّ اللَّهَ لاَ يَنْظُرُ إِلَى صُوَرِكُمْ وَأَمْوَالِكُمْ وَلَكِنْ يَنْظُرُ إِلَى قُلُوبِكُمْ وَأَعْمَالِكُمْ.",
      bengaliTranslation = "নিশ্চয়ই আল্লাহ তোমাদের বাহ্যিক রূপ এবং ধন-সম্পদের দিকে তাকান না; বরং তিনি দৃষ্টিপাত করেন তোমাদের অন্তর ও কর্মসমূহের প্রতি।",
      englishTranslation = "Verily, Allah does not look at your outward appearance or your wealth, but He looks at your hearts and your deeds.",
      grade = HadithGrade.SAHIH,
      reference = "সহীহ মুসলিম: ২৫৬৪"
    ),
    HadithItem(
      id = "bukhari_6011",
      bookId = "bukhari",
      bookNameBn = "সহীহ বুখারী",
      hadithNumber = 6011,
      chapterBn = "উত্তম শিষ্টাচার অধ্যায়",
      chapterEn = "Good Character",
      narratorBn = "আবদুল্লাহ ইবনু আমর (রা.)",
      narratorEn = "Abdullah ibn Amr (RA)",
      arabicText = "إِنَّ مِنْ خِيَارِكُمْ أَحْسَنَكُمْ أَخْلاَقًا.",
      bengaliTranslation = "রাসুলুল্লাহ ﷺ বলেছেন: তোমাদের মধ্যে সর্বশ্রেষ্ঠ ব্যক্তি সেই, যার চরিত্র ও ব্যবহার সবচেয়ে উত্তম।",
      englishTranslation = "The best among you are those who have the best manners and character.",
      grade = HadithGrade.MUTTAFAAQ_ALAYH,
      reference = "সহীহ বুখারী: ৬০১১, মুসলিম: ২৩২১"
    ),
    HadithItem(
      id = "tirmidhi_2317",
      bookId = "tirmidhi",
      bookNameBn = "জামে আত-তিরমিজী",
      hadithNumber = 2317,
      chapterBn = "জুহদ (দুনিয়াবিমুখতা) অধ্যায়",
      chapterEn = "Zuhd",
      narratorBn = "আবু হুরায়রা (রা.)",
      narratorEn = "Abu Hurairah (RA)",
      arabicText = "مِنْ حُسْنِ إِسْلاَمِ الْمَرْءِ تَرْكُهُ مَا لاَ يَعْنِيهِ.",
      bengaliTranslation = "কোনো ব্যক্তির ইসলামের সৌন্দর্য ও পূর্ণতার অন্যতম লক্ষণ হলো, অনর্থক ও অপ্রয়োজনীয় বিষয় ত্যাগ করা।",
      englishTranslation = "Part of the perfection of one's Islam is his leaving that which does not concern him.",
      grade = HadithGrade.HASAN,
      reference = "জামে আত-তিরমিজী: ২৩১৭, ইবনে মাজাহ: ৩৯৭৬"
    ),
    HadithItem(
      id = "muslim_2699",
      bookId = "muslim",
      bookNameBn = "সহীহ মুসলিম",
      hadithNumber = 2699,
      chapterBn = "আল্লাহর জিকর ও দোয়া অধ্যায়",
      chapterEn = "Remembrance & Knowledge",
      narratorBn = "আবু হুরায়রা (রা.)",
      narratorEn = "Abu Hurairah (RA)",
      arabicText = "مَنْ سَلَكَ طَرِيقًا يَلْتَمِسُ فِيهِ عِلْمًا سَهَّلَ اللَّهُ لَهُ بِهِ طَرِيقًا إِلَى الْجَنَّةِ.",
      bengaliTranslation = "যে ব্যক্তি জ্ঞান (দ্বীনি ইলম) অন্বেষণের জন্য কোনো পথ অবলম্বন করে, আল্লাহ তার জন্য জান্নাতের পথ সুগম করে দেন।",
      englishTranslation = "Whoever follows a path in pursuit of knowledge, Allah will make easy for him the path to Paradise.",
      grade = HadithGrade.SAHIH,
      reference = "সহীহ মুসলিম: ২৬৯৯"
    ),
    HadithItem(
      id = "abudawud_4941",
      bookId = "abudawud",
      bookNameBn = "সুনান আবু দাউদ",
      hadithNumber = 4941,
      chapterBn = "দয়া ও অনুগ্রহ অধ্যায়",
      chapterEn = "Mercy",
      narratorBn = "আবদুল্লাহ ইবনু আমর (রা.)",
      narratorEn = "Abdullah ibn Amr (RA)",
      arabicText = "الرَّاحِمُونَ يَرْحَمُهُمُ الرَّحْمَنُ، ارْحَمُوا مَنْ فِي الأَرْضِ يَرْحَمْكُمْ مَنْ فِي السَّمَاءِ.",
      bengaliTranslation = "দয়াকারীদের প্রতি পরম দয়ালু রহমান দয়া করেন। তোমরা জমিনে যারা আছে তাদের প্রতি দয়া করো, তাহলে আকাশে যিনি আছেন তিনি তোমাদের প্রতি দয়া করবেন।",
      englishTranslation = "Those who are merciful will be shown mercy by the Most Merciful. Be merciful to those on the earth and the One above the heavens will have mercy upon you.",
      grade = HadithGrade.SAHIH,
      reference = "সুনান আবু দাউদ: ৪৯৪১, তিরমিজী: ১৯২৪"
    ),
    HadithItem(
      id = "nasai_3104",
      bookId = "nasai",
      bookNameBn = "সুনান আন-নাসায়ী",
      hadithNumber = 3104,
      chapterBn = "পিতা-মাতার খেদমত অধ্যায়",
      chapterEn = "Parents & Family",
      narratorBn = "মুআবিয়া ইবনু জাহেমা (রা.)",
      narratorEn = "Mu'awiyah ibn Jahima (RA)",
      arabicText = "الْزَمْ رِجْلَهَا فَثَمَّ الْجَنَّةُ.",
      bengaliTranslation = "রাসুলুল্লাহ ﷺ জনৈক সাহাবীকে মায়ের সেবা সম্পর্কে বলেছিলেন: তুমি মায়ের পায়ের কাছে লেগে থাকো (তার খেদমত করো), কেননা সেখানেই রয়েছে তোমার জান্নাত।",
      englishTranslation = "Stay at her feet (serve your mother), for there is Paradise.",
      grade = HadithGrade.SAHIH,
      reference = "সুনান আন-নাসায়ী: ৩১০৪, আহমাদ: ১৫৫৭৭"
    ),
    HadithItem(
      id = "ibnmajah_224",
      bookId = "ibnmajah",
      bookNameBn = "সুনান ইবনে মাজাহ",
      hadithNumber = 224,
      chapterBn = "সুন্নাহর পরিচিতি অধ্যায়",
      chapterEn = "Seeking Knowledge",
      narratorBn = "আনাস ইবনু মালিক (রা.)",
      narratorEn = "Anas ibn Malik (RA)",
      arabicText = "طَلَبُ الْعِلْمِ فَرِيضَةٌ عَلَى كُلِّ مُسْلِمٍ.",
      bengaliTranslation = "দ্বীনি জ্ঞান অর্জন করা প্রত্যেক মুসলমানের ওপর ফরজ (অপরিহার্য দায়িত্ব)।",
      englishTranslation = "Seeking religious knowledge is an obligation upon every Muslim.",
      grade = HadithGrade.HASAN,
      reference = "সুনান ইবনে মাজাহ: ২২৪, বায়হাকী: ১৬৬৫"
    ),
    HadithItem(
      id = "nawawi_2",
      bookId = "nawawi",
      bookNameBn = "চল্লিশ হাদিস (ইমাম নববী)",
      hadithNumber = 2,
      chapterBn = "হাদিসে জিবরীল (ইসলাম, ঈমান ও ইহসান)",
      chapterEn = "Hadith of Jibril",
      narratorBn = "উমর ইবনুল খাত্তাব (রা.)",
      narratorEn = "Umar ibn al-Khattab (RA)",
      arabicText = "أَنْ تَعْبُدَ اللَّهَ كَأَنَّكَ تَرَاهُ، فَإِنْ لَمْ تَكُنْ تَرَاهُ فَإِنَّهُ يَرَاكَ.",
      bengaliTranslation = "জিবরীল (আ.) ইহসান সম্পর্কে জিজ্ঞেস করলে রাসুলুল্লাহ ﷺ বলেন: তুমি এমনভাবে আল্লাহর ইবাদত করবে যেন তুমি তাঁকে দেখছ; আর যদি তুমি তাঁকে দেখতে না পাও, তবে জেনে রাখবে তিনি নিশ্চয়ই তোমাকে দেখছেন।",
      englishTranslation = "Ihsan is to worship Allah as if you see Him, and if you do not see Him, then indeed He sees you.",
      grade = HadithGrade.MUTTAFAAQ_ALAYH,
      reference = "সহীহ মুসলিম: ৮, সহীহ বুখারী: ৫০"
    )
  )

  val dailyHadith: HadithItem = hadiths[2] // Verily Allah does not look at your appearance but your hearts
}
