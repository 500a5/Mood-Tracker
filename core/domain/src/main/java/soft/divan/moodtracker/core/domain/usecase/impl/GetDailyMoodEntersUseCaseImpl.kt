package soft.divan.moodtracker.core.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.core.domain.usecase.GetDailyMoodEntersUseCase
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.repository.DailyMoodRepository
import javax.inject.Inject

class GetDailyMoodEntersUseCaseImpl @Inject constructor(
    private val accountRepository: DailyMoodRepository
) : GetDailyMoodEntersUseCase
{
    override suspend fun invoke(): Flow<List<DailyMoodEntry>> {
        return accountRepository.getDailyMood()
    }
}