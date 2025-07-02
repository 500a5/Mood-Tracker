package soft.divan.moodtracker.feature.analytics.model

import soft.divan.moodtracker.core.domain.model.DailyMoodEntry

sealed interface DailyMoodEntriesUiState {
    data object Loading : DailyMoodEntriesUiState
    data class Success(
        val dailyMoodEntry: List<DailyMoodEntry>,
        val aiText: String
    ) : DailyMoodEntriesUiState

    data class Error(val message: String) : DailyMoodEntriesUiState
}

