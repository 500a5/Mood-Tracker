package soft.divan.moodtracker.core.domain.usecase.impl

import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.repository.DailyMoodRepository
import soft.divan.moodtracker.core.domain.usecase.SaveDailyMoodEntryUseCase
import javax.inject.Inject

class SaveDailyMoodEntryUseCaseImpl @Inject constructor(
    private val repository: DailyMoodRepository
):SaveDailyMoodEntryUseCase {
    override suspend operator fun invoke(entry: DailyMoodEntry) {
        repository.saveDailyMood(entry)
    }
}