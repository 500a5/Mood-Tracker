package soft.divan.moodtracker.feature.analytics.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import soft.divan.moodtracker.core.domain.model.ChatMessage
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.model.DayMoodRating
import soft.divan.moodtracker.core.domain.model.EmotionCategory
import soft.divan.moodtracker.core.domain.model.HabitType
import soft.divan.moodtracker.core.domain.model.HealthState
import soft.divan.moodtracker.core.domain.model.HobbyCategory
import soft.divan.moodtracker.core.domain.model.NutritionQuality
import soft.divan.moodtracker.core.domain.model.SleepQuality
import soft.divan.moodtracker.core.domain.model.WeatherType
import soft.divan.moodtracker.core.domain.repository.GigaChatRepository
import soft.divan.moodtracker.core.domain.usecase.GetDailyMoodEntersUseCase
import soft.divan.moodtracker.feature.analytics.model.DailyMoodEntriesUiState
import javax.inject.Inject


@HiltViewModel
class MoodAnalyticsViewModel @Inject constructor(
    private val getDailyMoodEntersUseCase: GetDailyMoodEntersUseCase,
    private val gigaChatRepository: GigaChatRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<DailyMoodEntriesUiState>(DailyMoodEntriesUiState.Loading)
    val uiState: StateFlow<DailyMoodEntriesUiState> = _uiState.asStateFlow()
        .onStart { loadDailyMoodEnters() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            DailyMoodEntriesUiState.Loading
        )

    fun loadDailyMoodEnters() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getDailyMoodEntersUseCase.invoke().first()
            val ailist = mapDailyMoodEntriesToAIRequest(list)
            val chat = gigaChatRepository.analyzeMessages(
                listOf(
                    ChatMessage(
                        "system",
                        "Ты — заботливый помощник. Проанализируй эмоциональные данные."
                    ),
                    ChatMessage("user", ailist)
                )
            )
            _uiState.update {
                DailyMoodEntriesUiState.Success(
                    dailyMoodEntry = list,
                    aiText = chat
                )
            }
        }
    }

    fun mapDailyMoodEntriesToAIRequest(entries: List<DailyMoodEntry>): String {
        // Вспомогательные маппинги для enum -> человекочитаемое описание на русском
        val moodMap = mapOf(
            DayMoodRating.AMAZING to "Отлично",
            DayMoodRating.GOOD to "Хорошо",
            DayMoodRating.NORMAL to "Нормально",
            DayMoodRating.BAD to "Плохо",
            DayMoodRating.AWFUL to "Ужасно"
        )

        val emotionMap = mapOf(
            EmotionCategory.HAPPY to "Счастье",
            EmotionCategory.ANXIOUS to "Тревога",
            EmotionCategory.SAD to "Грусть",
            EmotionCategory.ANGRY to "Злость",
            EmotionCategory.GRATEFUL to "Благодарность",
            EmotionCategory.CALM to "Спокойствие",
            EmotionCategory.LONELY to "Одиночество",
            EmotionCategory.PROUD to "Гордость",
            EmotionCategory.STRESS to "Стресс",
            EmotionCategory.RELAXATION to "Расслабление",
            EmotionCategory.PRODUCTIVITY to "Продуктивность",
            EmotionCategory.CONDITION to "Усталость",
            EmotionCategory.UNCERTAINTY to "Неуверенность"
        )

        val nutritionMap = mapOf(
            NutritionQuality.HEALTHY to "Здоровое",
            NutritionQuality.OVEREAT to "Переедание",
            NutritionQuality.SKIPPED_MEAL to "Пропущенный прием пищи",
            NutritionQuality.JUNK_FOOD to "Фастфуд"
        )

        val hobbyMap = mapOf(
            HobbyCategory.READING to "Чтение",
            HobbyCategory.SPORTS to "Спорт",
            HobbyCategory.MUSIC to "Музыка",
            HobbyCategory.ART to "Искусство",
            HobbyCategory.WALKING to "Прогулки",
            HobbyCategory.GAMES to "Игры",
            HobbyCategory.OTHER to "Другое"
        )

        val healthMap = mapOf(
            HealthState.HEALTHY to "Здоров",
            HealthState.HEADACHE to "Головная боль",
            HealthState.STOMACHACHE to "Боль в животе",
            HealthState.TOOTHACHE to "Зубная боль",
            HealthState.FATIGUE to "Усталость",
            HealthState.ILLNESS to "Болезнь",
            HealthState.OTHER to "Другое"
        )

        val weatherMap = mapOf(
            WeatherType.SUNNY to "Солнечно",
            WeatherType.CLOUDY to "Облачно",
            WeatherType.RAINY to "Дождь",
            WeatherType.SNOWY to "Снег",
            WeatherType.WINDY to "Ветрено",
            WeatherType.FOGGY to "Туман"
        )

        val habitMap = mapOf(
            HabitType.ALCOHOL to "Алкоголь",
            HabitType.SMOKING to "Курение",
            HabitType.CAFFEINE to "Кофеин",
            HabitType.SCREEN_TIME to "Время перед экраном",
            HabitType.MEDITATION to "Медитация",
            HabitType.EXERCISE to "Физическая активность"
        )

        fun listToString(list: List<*>, map: Map<*, String>): String {
            return if (list.isEmpty()) "нет" else list.joinToString(", ") { item ->
                map[item] ?: item.toString()
            }
        }

        fun mapSleepQuality(sleep: SleepQuality): String {
            return "${sleep.durationInHours} часов, " +
                    "поздно лег спать: ${if (sleep.wentToBedLate) "да" else "нет"}, " +
                    "просыпался ночью: ${if (sleep.wokeUpOften) "да" else "нет"}, " +
                    "просыпался уставшим: ${if (sleep.wokeUpTired) "да" else "нет"}"
        }

        return entries.joinToString(separator = "\n\n") { entry ->
            buildString {
                appendLine("Дата: ${entry.date}")
                appendLine("Настроение: ${moodMap[entry.moodRating] ?: entry.moodRating}")
                appendLine("Эмоции: ${listToString(entry.emotions, emotionMap)}")
                appendLine("Сон: ${mapSleepQuality(entry.sleep)}")
                appendLine("Питание: ${listToString(entry.nutrition, nutritionMap)}")
                appendLine("Хобби: ${listToString(entry.hobbies, hobbyMap)}")
                appendLine("Состояние здоровья: ${listToString(entry.health, healthMap)}")
                appendLine("Погода: ${entry.weather?.let { weatherMap[it] } ?: "нет данных"}")
                appendLine("Привычки: ${listToString(entry.habits, habitMap)}")
                appendLine("Заметка: ${if (entry.note.isNotBlank()) entry.note else "нет"}")
            }
        }
    }


}
