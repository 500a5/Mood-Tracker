package soft.divan.moodtracker.feature.create.domain.usecase

import soft.divan.moodtracker.core.model.DailyMoodEntry

interface SaveDailyMoodEntryUseCase {
    suspend operator fun invoke(entry: DailyMoodEntry)
}