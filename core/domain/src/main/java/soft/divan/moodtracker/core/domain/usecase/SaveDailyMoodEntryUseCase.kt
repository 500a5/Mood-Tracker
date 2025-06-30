package soft.divan.moodtracker.core.domain.usecase

import soft.divan.moodtracker.core.domain.model.DailyMoodEntry

interface SaveDailyMoodEntryUseCase {
    suspend operator fun invoke(entry: DailyMoodEntry)
}