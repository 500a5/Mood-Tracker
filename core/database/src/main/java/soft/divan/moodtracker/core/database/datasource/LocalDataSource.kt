package soft.divan.moodtracker.core.database.datasource

import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry


interface LocalDataSource {
    suspend fun save(entry: DailyMoodEntry)
    suspend fun getDayMoodEntry(): Flow<List<DailyMoodEntry>>
}
