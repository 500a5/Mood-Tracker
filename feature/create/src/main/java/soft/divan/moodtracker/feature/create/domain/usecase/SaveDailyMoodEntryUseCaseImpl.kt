package soft.divan.moodtracker.feature.create.domain.usecase

import soft.divan.moodtracker.core.model.DailyMoodEntry
import soft.divan.moodtracker.feature.create.domain.repository.DailyMoodRepository
import javax.inject.Inject

class SaveDailyMoodEntryUseCaseImpl @Inject constructor(
    private val repository: DailyMoodRepository
):SaveDailyMoodEntryUseCase {
    override suspend operator fun invoke(entry: DailyMoodEntry) {
        repository.saveDailyMood(entry)
    }
}