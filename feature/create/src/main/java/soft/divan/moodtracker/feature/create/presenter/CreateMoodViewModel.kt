package soft.divan.moodtracker.feature.create.presenter


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import soft.divan.moodtracker.core.model.DailyMoodEntry
import soft.divan.moodtracker.core.model.DayMoodRating
import soft.divan.moodtracker.core.model.EmotionCategory
import soft.divan.moodtracker.core.model.HabitType
import soft.divan.moodtracker.core.model.HealthState
import soft.divan.moodtracker.core.model.HobbyCategory
import soft.divan.moodtracker.core.model.NutritionQuality
import soft.divan.moodtracker.core.model.SleepQuality
import soft.divan.moodtracker.core.model.WeatherType
import javax.inject.Inject

@HiltViewModel
class CreateMoodViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DailyMoodEntry())
    val uiState: StateFlow<DailyMoodEntry> = _uiState.asStateFlow()

    fun updateMood(mood: DayMoodRating) {
        _uiState.update { it.copy(moodRating = mood) }
    }

    fun updateSleepQuality(sleepQuality: SleepQuality) {
        _uiState.update { it.copy(sleep = sleepQuality) }
    }

    fun toggleEmotion(emotion: EmotionCategory) {
        _uiState.update {
            val updatedList = it.emotions.toMutableList()
            if (updatedList.contains(emotion)) {
                updatedList.remove(emotion)
            } else {
                updatedList.add(emotion)
            }
            it.copy(emotions = updatedList)
        }
    }

    fun toggleNutrition(nutrition: NutritionQuality) {
        _uiState.update {
            val updatedList = it.nutrition.toMutableList()
            if (updatedList.contains(nutrition)) {
                updatedList.remove(nutrition)
            } else {
                updatedList.add(nutrition)
            }
            it.copy(nutrition = updatedList)
        }
    }

    fun toggleHobby(hobby: HobbyCategory) {
        _uiState.update {
            val updatedList = it.hobbies.toMutableList()
            if (updatedList.contains(hobby)) {
                updatedList.remove(hobby)
            } else {
                updatedList.add(hobby)
            }
            it.copy(hobbies = updatedList)
        }
    }

    fun toggleHealthState(state: HealthState) {
        _uiState.update {
            val updatedList = it.health.toMutableList()
            if (updatedList.contains(state)) {
                updatedList.remove(state)
            } else {
                updatedList.add(state)
            }
            it.copy(health = updatedList)
        }
    }

    fun updateWeather(weather: WeatherType) {
        _uiState.update { it.copy(weather = weather) }
    }

    fun toggleHabit(habit: HabitType) {
        _uiState.update {
            val updatedList = it.habits.toMutableList()
            if (updatedList.contains(habit)) {
                updatedList.remove(habit)
            } else {
                updatedList.add(habit)
            }
            it.copy(habits = updatedList)
        }
    }

    fun updateNote(note: String) {
        _uiState.update { it.copy(note = note) }
    }

    fun saveDay() {
        val currentState = _uiState.value
        // Здесь можно сохранить в базу данных или отправить на сервер
        // Пример:
        // repository.saveDayMood(currentState)
    }
}
