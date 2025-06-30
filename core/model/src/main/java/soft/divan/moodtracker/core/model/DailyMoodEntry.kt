package soft.divan.moodtracker.core.model

import java.time.LocalDate
import java.util.UUID

// Основная запись пользователя на день
data class DailyMoodEntry(
    val id: String = UUID.randomUUID().toString(),
    val date: LocalDate = LocalDate.now(),
    val moodRating: DayMoodRating = DayMoodRating.NORMAL,
    val emotions: List<EmotionCategory> = emptyList(),
    val sleep: SleepQuality = SleepQuality(),
    val nutrition:  List<NutritionQuality> = emptyList(),
    val hobbies: List<HobbyCategory> = emptyList(),
    val health: List<HealthState> = emptyList(),
    val weather: WeatherType?= null,
    val habits: List<HabitType> = emptyList(),
    val note: String = ""
)