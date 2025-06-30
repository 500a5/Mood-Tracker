package soft.divan.moodtracker.feature.entries.model

import soft.divan.moodtracker.core.domain.model.DailyMoodEntry


sealed interface DailyMoodEntriesUiState {
    data object Loading : DailyMoodEntriesUiState
    data class Success(
        val dailyMoodEntry: List<DailyMoodEntry>,
    ) : DailyMoodEntriesUiState

    data class Error(val message: String) : DailyMoodEntriesUiState
}

