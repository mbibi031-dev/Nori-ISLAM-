package com.example

import com.example.data.repository.AcademyData
import com.example.data.repository.DuaAndTasbihData
import com.example.data.repository.HadithData
import com.example.data.repository.QuranData
import com.example.data.repository.SalahData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun testQuranSurahsCatalogCompleteness() {
    val surahs = QuranData.surahs
    assertEquals(114, surahs.size)
    assertEquals(1, surahs.first().number)
    assertEquals("আল-ফাতিহা", surahs.first().nameBengali)
    assertEquals(114, surahs.last().number)
    assertEquals("আন-নাস", surahs.last().nameBengali)
  }

  @Test
  fun testHadithCollectionsAuthenticity() {
    val hadiths = HadithData.hadiths
    assertTrue(hadiths.isNotEmpty())
    hadiths.forEach { hadith ->
      assertTrue(hadith.arabicText.isNotBlank())
      assertTrue(hadith.bengaliTranslation.isNotBlank())
      assertTrue(hadith.reference.isNotBlank())
      assertNotNull(hadith.grade)
    }
  }

  @Test
  fun testSalahStepsSequence() {
    val steps = SalahData.salahSteps
    assertEquals(9, steps.size)
    assertEquals(1, steps.first().stepNumber)
    assertEquals(9, steps.last().stepNumber)
  }

  @Test
  fun testAcademyCoursesAndQuizzes() {
    val courses = AcademyData.courses
    assertTrue(courses.isNotEmpty())
    courses.forEach { course ->
      assertTrue(course.lessons.isNotEmpty())
      assertTrue(course.quiz.questions.isNotEmpty())
      course.quiz.questions.forEach { q ->
        assertTrue(q.correctOptionIndex in q.optionsBn.indices)
      }
    }
  }

  @Test
  fun testTasbihPresetsAndDuas() {
    val presets = DuaAndTasbihData.tasbihPresets
    val duas = DuaAndTasbihData.duas
    assertTrue(presets.isNotEmpty())
    assertTrue(duas.isNotEmpty())
  }
}
