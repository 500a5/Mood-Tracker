package soft.divan.moodtracker.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry

interface GetDailyMoodEntersUseCase {
    suspend operator fun invoke(): Flow<List<DailyMoodEntry>>
}

