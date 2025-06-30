package soft.divan.moodtracker.core.domain.repository


import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry

interface DailyMoodRepository {
    suspend fun saveDailyMood(entry: DailyMoodEntry)
    suspend fun getDailyMood(): Flow<List<DailyMoodEntry>>
}
