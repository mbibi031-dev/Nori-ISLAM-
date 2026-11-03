package com.example.ui.screens.academy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Course
import com.example.data.model.Lesson
import com.example.data.repository.AcademyData
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiState

@Composable
fun AcademyScreen(
  uiState: UiState,
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  if (uiState.showCertificate && uiState.selectedCourse != null) {
    CertificateView(
      course = uiState.selectedCourse,
      studentName = uiState.studentCertificateName,
      score = uiState.quizScore,
      onBack = { viewModel.closeCourseDetail() },
      onNameChange = { viewModel.updateCertificateName(it) },
      modifier = modifier
    )
  } else if (uiState.isQuizActive && uiState.selectedCourse != null) {
    QuizInteractiveView(
      course = uiState.selectedCourse,
      uiState = uiState,
      viewModel = viewModel,
      onBack = { viewModel.openCourse(uiState.selectedCourse) },
      modifier = modifier
    )
  } else if (uiState.selectedCourse != null) {
    CourseDetailView(
      course = uiState.selectedCourse,
      uiState = uiState,
      viewModel = viewModel,
      onBack = { viewModel.closeCourseDetail() },
      modifier = modifier
    )
  } else {
    AcademyCourseList(
      courses = AcademyData.courses,
      completedLessons = uiState.completedLessons,
      onSelectCourse = { viewModel.openCourse(it) },
      modifier = modifier
    )
  }
}

@Composable
fun AcademyCourseList(
  courses: List<Course>,
  completedLessons: Set<String>,
  onSelectCourse: (Course) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp, bottom = 90.dp)
  ) {
    item {
      // Banner
      val gradient = Brush.linearGradient(listOf(Emerald900, Emerald700))
      Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(20.dp))
          .border(1.dp, Gold500.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(gradient)
            .padding(18.dp)
        ) {
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.School,
                contentDescription = null,
                tint = Gold300,
                modifier = Modifier.size(28.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "ইসলামিক শিক্ষা একাডেমি",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "বিশুদ্ধ আকীদা, পাঁচ স্তম্ভ, সীরাত ও নৈতিক চরিত্রের ওপর সনদভিত্তিক কাঠামোগত কোর্সসমূহ সম্পন্ন করুন।",
              style = MaterialTheme.typography.bodySmall.copy(color = Emerald100, lineHeight = 18.sp)
            )
          }
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
    }

    item {
      Text(
        text = "উপলব্ধ কোর্সসমূহ (${courses.size}টি কোর্স)",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
      )
      Spacer(modifier = Modifier.height(8.dp))
    }

    items(courses, key = { it.id }) { course ->
      CourseCard(
        course = course,
        completedLessonCount = course.lessons.count { completedLessons.contains(it.id) },
        onClick = { onSelectCourse(course) }
      )
    }
  }
}

@Composable
fun CourseCard(
  course: Course,
  completedLessonCount: Int,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val progress = if (course.totalLessons > 0) completedLessonCount.toFloat() / course.totalLessons else 0f

  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 6.dp)
      .clickable { onClick() }
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Emerald100
        ) {
          Text(
            text = course.categoryBn,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = Emerald800
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Text(
          text = "সময়কাল: ${course.duration}",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = course.titleBn,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
      )

      Text(
        text = course.descriptionBn,
        style = MaterialTheme.typography.bodySmall.copy(
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          lineHeight = 18.sp
        ),
        maxLines = 2
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Progress bar
      LinearProgressIndicator(
        progress = progress,
        color = Emerald700,
        trackColor = Emerald50,
        modifier = Modifier
          .fillMaxWidth()
          .height(6.dp)
          .clip(RoundedCornerShape(3.dp))
      )

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = "$completedLessonCount/${course.totalLessons} পাঠ সম্পন্ন",
          style = MaterialTheme.typography.labelSmall.copy(color = Emerald700)
        )
        Surface(
          shape = RoundedCornerShape(6.dp),
          color = Gold100
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Icon(
              imageVector = Icons.Default.WorkspacePremium,
              contentDescription = null,
              tint = Gold600,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "সনদ অন্তর্ভুক্ত",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = Gold600
            )
          }
        }
      }
    }
  }
}

@Composable
fun CourseDetailView(
  course: Course,
  uiState: UiState,
  viewModel: MainViewModel,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedLesson by remember { mutableStateOf<Lesson?>(course.lessons.firstOrNull()) }

  Column(modifier = modifier.fillMaxSize()) {
    // Header
    Surface(
      color = Emerald800,
      tonalElevation = 4.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 8.dp, vertical = 10.dp)
      ) {
        IconButton(onClick = onBack) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
          )
        }
        Column {
          Text(
            text = course.titleBn,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          )
          Text(
            text = "${course.totalLessons}টি পাঠ • কুইজ ও সনদ পরীক্ষা",
            style = MaterialTheme.typography.labelSmall.copy(color = Emerald100)
          )
        }
      }
    }

    LazyColumn(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth(),
      contentPadding = PaddingValues(16.dp, bottom = 90.dp)
    ) {
      // Current active lesson view
      if (selectedLesson != null) {
        item {
          val lesson = selectedLesson!!
          val isCompleted = uiState.completedLessons.contains(lesson.id)

          Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = androidx.compose.foundation.BorderStroke(1.dp, Emerald300),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
              ) {
                Text(
                  text = "পাঠ ${lesson.lessonNumber}: ${lesson.titleBn}",
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Emerald800
                  )
                )
                Text(
                  text = lesson.readTime,
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }

              Spacer(modifier = Modifier.height(10.dp))

              Text(
                text = lesson.contentBn,
                style = MaterialTheme.typography.bodyMedium.copy(
                  lineHeight = 22.sp,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )

              if (lesson.keyPointsBn.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                  text = "মূল শিক্ষণীয় পয়েন্টসমূহ:",
                  style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Emerald700
                  )
                )
                Spacer(modifier = Modifier.height(6.dp))
                lesson.keyPointsBn.forEach { point ->
                  Row(modifier = Modifier.padding(vertical = 2.dp)) {
                    Text(text = "✓ ", color = Emerald700, fontWeight = FontWeight.Bold)
                    Text(
                      text = point,
                      style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                  }
                }
              }

              if (lesson.quranOrHadithRef.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = Emerald50,
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Text(
                    text = "দলিল: ${lesson.quranOrHadithRef}",
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.SemiBold,
                      color = Emerald800
                    ),
                    modifier = Modifier.padding(8.dp)
                  )
                }
              }

              Spacer(modifier = Modifier.height(14.dp))

              Button(
                onClick = { viewModel.markLessonCompleted(lesson.id) },
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (isCompleted) Emerald700 else Emerald800
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
              ) {
                Icon(
                  imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Check,
                  contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (isCompleted) "পাঠ সম্পন্ন হয়েছে" else "পাঠ সম্পন্ন চিহ্নিত করুন")
              }
            }
          }
          Spacer(modifier = Modifier.height(16.dp))
        }
      }

      // Lessons Navigation list
      item {
        Text(
          text = "কোর্সের সকল পাঠ তালিকা",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
      }

      items(course.lessons, key = { it.id }) { lesson ->
        val isDone = uiState.completedLessons.contains(lesson.id)
        val isSelected = selectedLesson?.id == lesson.id

        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Emerald50 else MaterialTheme.colorScheme.surface
          ),
          border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) Emerald700 else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
          ),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { selectedLesson = lesson }
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(12.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.MenuBook,
                contentDescription = null,
                tint = if (isDone) Emerald700 else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(
                  text = "${lesson.lessonNumber}. ${lesson.titleBn}",
                  style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                  )
                )
                Text(
                  text = "সময়কাল: ${lesson.readTime}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
          }
        }
      }

      // Quiz & Certificate Action Card
      item {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
          shape = RoundedCornerShape(18.dp),
          colors = CardDefaults.cardColors(containerColor = Gold100),
          border = androidx.compose.foundation.BorderStroke(1.dp, Gold400),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Quiz,
              contentDescription = null,
              tint = Gold600,
              modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "কোর্স সমাপনী কুইজ পরীক্ষা",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Gold600
              )
            )
            Text(
              text = "৮০% বা তার বেশি স্কোর অর্জন করে আপনার ভেরিফাইড সনদপত্র সংগ্রহ করুন।",
              style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
              )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
              onClick = { viewModel.startQuiz() },
              colors = ButtonDefaults.buttonColors(containerColor = Gold600),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text("কুইজ শুরু করুন (${course.quiz.questions.size}টি প্রশ্ন)")
            }
          }
        }
      }
    }
  }
}

@Composable
fun QuizInteractiveView(
  course: Course,
  uiState: UiState,
  viewModel: MainViewModel,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val quiz = course.quiz
  val currentQuestion = quiz.questions.getOrNull(uiState.currentQuizQuestionIndex)

  Column(modifier = modifier.fillMaxSize()) {
    // Header
    Surface(
      color = Emerald800,
      tonalElevation = 4.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 8.dp, vertical = 10.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(onClick = onBack) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White
            )
          }
          Text(
            text = "কুইজ: ${quiz.titleBn}",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          )
        }
        Text(
          text = "স্কোর: ${uiState.quizScore}%",
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = Gold300
          ),
          modifier = Modifier.padding(end = 12.dp)
        )
      }
    }

    if (currentQuestion != null && !uiState.isQuizFinished) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp)
      ) {
        // Progress bar
        LinearProgressIndicator(
          progress = (uiState.currentQuizQuestionIndex + 1).toFloat() / quiz.questions.size.coerceAtLeast(1),
          color = Emerald700,
          trackColor = Emerald50,
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = "প্রশ্ন ${uiState.currentQuizQuestionIndex + 1}/${quiz.questions.size}",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Question Card
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = currentQuestion.questionBn,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
              )
            )
            if (currentQuestion.questionEn.isNotBlank()) {
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = currentQuestion.questionEn,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Options List
        currentQuestion.optionsBn.forEachIndexed { index, option ->
          val isSelected = uiState.selectedQuizAnswerIndex == index
          val isSubmitted = uiState.quizAnswerSubmitted
          val isCorrect = index == currentQuestion.correctOptionIndex

          val bgColor = when {
            isSubmitted && isCorrect -> Color(0xFFE8F5E9)
            isSubmitted && isSelected && !isCorrect -> Color(0xFFFFEBEE)
            isSelected -> Emerald100
            else -> MaterialTheme.colorScheme.surface
          }

          val borderColor = when {
            isSubmitted && isCorrect -> Color(0xFF2E7D32)
            isSubmitted && isSelected && !isCorrect -> Color(0xFFC62828)
            isSelected -> Emerald700
            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
          }

          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = bgColor),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor),
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp)
              .clickable(enabled = !isSubmitted) {
                viewModel.selectQuizAnswer(index)
              }
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(14.dp)
            ) {
              Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                  .size(28.dp)
                  .clip(CircleShape)
                  .background(if (isSelected) Emerald700 else Color.White)
                  .border(1.dp, Emerald700, CircleShape)
              ) {
                Text(
                  text = "${'A' + index}",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else Emerald800
                  )
                )
              }
              Spacer(modifier = Modifier.width(12.dp))
              Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
              )
            }
          }
        }

        // Explanation on submit
        if (uiState.quizAnswerSubmitted) {
          Spacer(modifier = Modifier.height(12.dp))
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Emerald50,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = currentQuestion.explanationBn,
              style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = Emerald900
              ),
              modifier = Modifier.padding(12.dp)
            )
          }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Action Button
        if (!uiState.quizAnswerSubmitted) {
          Button(
            onClick = { viewModel.submitQuizAnswer() },
            enabled = uiState.selectedQuizAnswerIndex != null,
            colors = ButtonDefaults.buttonColors(containerColor = Emerald700),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("উত্তর জমা দিন")
          }
        } else {
          Button(
            onClick = { viewModel.nextQuizQuestion() },
            colors = ButtonDefaults.buttonColors(containerColor = Emerald700),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              if (uiState.currentQuizQuestionIndex < quiz.questions.size - 1) "পরবর্তী প্রশ্ন" else "ফলাফল দেখুন"
            )
          }
        }
      }
    } else {
      // Quiz Finished Result Card
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
          .fillMaxSize()
          .padding(24.dp)
      ) {
        val passed = uiState.quizScore >= quiz.passingScore

        Icon(
          imageVector = if (passed) Icons.Default.WorkspacePremium else Icons.Default.Close,
          contentDescription = null,
          tint = if (passed) Gold600 else Color.Red,
          modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
          text = if (passed) "মাশাআল্লাহ! আপনি উত্তীর্ণ হয়েছেন" else "দুঃখিত! পুনরায় চেষ্টা করুন",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
          text = "আপনার প্রাপ্ত স্কোর: ${uiState.quizScore}% (পাসিং মার্ক: ${quiz.passingScore}%)",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (passed) {
          Button(
            onClick = { /* trigger certificate view */ },
            colors = ButtonDefaults.buttonColors(containerColor = Gold600),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(imageVector = Icons.Default.CardMembership, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("সনদপত্র গ্রহণ করুন")
          }
        } else {
          Button(
            onClick = { viewModel.startQuiz() },
            colors = ButtonDefaults.buttonColors(containerColor = Emerald700),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("আবার পরীক্ষা দিন")
          }
        }
      }
    }
  }
}

@Composable
fun CertificateView(
  course: Course,
  studentName: String,
  score: Int,
  onBack: () -> Unit,
  onNameChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var isEditingName by remember { mutableStateOf(false) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      IconButton(onClick = onBack) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
      }
      Text(
        text = "অর্জিত সনদপত্র",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
      )
      Spacer(modifier = Modifier.width(48.dp))
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Certificate Card Container
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFFFCFBF7)),
      border = androidx.compose.foundation.BorderStroke(3.dp, Gold500),
      modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        // Decorative Islamic header
        Text(
          text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
          style = ArabicTitleStyle.copy(
            fontSize = 20.sp,
            color = Emerald800,
            textAlign = TextAlign.Center
          )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "নূরে ইসলাম একাডেমি",
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = Gold600
          )
        )

        Text(
          text = "সনদপত্র / CERTIFICATE OF COMPLETION",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "এই মর্মে প্রত্যয়ন করা যাচ্ছে যে,",
          style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = studentName,
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Emerald900
          )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "সাফল্যের সাথে '${course.titleBn}' কোর্সটি সম্পন্ন করেছেন এবং সমাপনী পরীক্ষায় $score% নম্বর অর্জন করেছেন।",
          style = MaterialTheme.typography.bodySmall.copy(
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
          )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = "৩ নভেম্বর ২০২৬",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = "তারিখ", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
          }

          Icon(
            imageVector = Icons.Default.WorkspacePremium,
            contentDescription = null,
            tint = Gold500,
            modifier = Modifier.size(44.dp)
          )

          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = "ভেরিফাইড সনদ",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Emerald700
              )
            )
            Text(text = "কর্তৃপক্ষ", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Student Name Input
    OutlinedTextField(
      value = studentName,
      onValueChange = onNameChange,
      label = { Text("সনদে আপনার নাম পরিবর্তন করুন") },
      shape = RoundedCornerShape(12.dp),
      singleLine = true,
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(12.dp))

    Button(
      onClick = onBack,
      colors = ButtonDefaults.buttonColors(containerColor = Emerald700),
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("কোর্স তালিকায় ফিরে যান")
    }
  }
}
