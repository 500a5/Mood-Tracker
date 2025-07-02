package soft.divan.moodtracker.feature.analytics.presenter

import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import soft.divan.designsystem.component.LoadingProgressBar
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.model.DayMoodRating
import soft.divan.moodtracker.feature.analytics.model.DailyMoodEntriesUiState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
fun MoodChartScreenPreview() {
    val sampleEntries = listOf(
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(1), moodRating = DayMoodRating.GOOD),
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(3), moodRating = DayMoodRating.AWFUL),
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(5), moodRating = DayMoodRating.AMAZING),
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(6), moodRating = DayMoodRating.NORMAL),
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(8), moodRating = DayMoodRating.BAD),
    )

    Chart(moodEntries = sampleEntries)

    AIRecommendationCard(
        recommendationText = "scdscdv",

        )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodAnalyticsScreen(
    navController: NavController,
    viewModel: MoodAnalyticsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is DailyMoodEntriesUiState.Error -> {}
        is DailyMoodEntriesUiState.Loading -> {
            LoadingProgressBar()
        }

        is DailyMoodEntriesUiState.Success -> {
            val list = (uiState as DailyMoodEntriesUiState.Success).dailyMoodEntry
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Chart(list, modifier = Modifier.padding(16.dp))

                AIRecommendationCard(
                    recommendationText = (uiState as DailyMoodEntriesUiState.Success).aiText,
                )
            }
        }
    }


}

@Composable
fun AIRecommendationCard(
    recommendationTitle: String = "Рекомендация от ИИ",
    recommendationText: String,
    emoji: String = "\uD83D\uDCA1", // лампочка по умолчанию
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Заголовок с эмоджи
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = emoji,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = recommendationTitle,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Текст рекомендации
            Text(
                text = recommendationText,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )

        }
    }
}

@Composable
fun Chart(
    moodEntries: List<DailyMoodEntry>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setTouchEnabled(true)
                isDragEnabled = true
                setScaleEnabled(true)
                animateX(1000)
                description.isEnabled = false
                legend.isEnabled = false
                axisRight.isEnabled = false
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        update = { chart ->
            val entries = moodEntries
                .sortedBy { it.date }
                .mapIndexed { index, moodEntry ->
                    Entry(index.toFloat(), moodEntry.moodRating.ordinal.toFloat())
                }

            val dataSet = LineDataSet(entries, "Mood").apply {
                color = android.graphics.Color.BLACK
                valueTextColor = android.graphics.Color.BLACK
                lineWidth = 2f
                circleRadius = 4f
                setDrawValues(false)
                setDrawCircles(true)
            }

            val data = LineData(dataSet)
            chart.data = data

            chart.xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setDrawGridLines(true)
                valueFormatter = IndexAxisValueFormatter(
                    moodEntries.sortedBy { it.date }
                        .map { it.date.dayOfMonth.toString() }
                )
            }

            chart.axisLeft.apply {
                axisMinimum = 0f
                axisMaximum = 4f
                granularity = 1f
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return when (value.toInt()) {
                            0 -> "😖"
                            1 -> "😟"
                            2 -> "😐"
                            3 -> "😊"
                            4 -> "🤩"
                            else -> ""
                        }
                    }
                }
            }

            chart.invalidate()
        }
    )
}

