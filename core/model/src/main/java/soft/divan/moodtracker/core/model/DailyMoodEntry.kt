package soft.divan.moodtracker.core.model

import java.time.LocalDate

// Основная запись пользователя на день
data class DailyMoodEntry(
    val date: LocalDate,
    val moodRating: DayMoodRating,
    val emotions: List<EmotionCategory>?,
    val sleep: SleepQuality?,
    val nutrition:  List<NutritionQuality>?,
    val hobbies: List<HobbyCategory>?,
    val health: List<HealthState>?,
    val weather: WeatherType?,
    val habits: List<HabitType>?,
    val note: String? = null
)