package soft.divan.moodtracker.core.database

import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.core.database.datasource.LocalDataSource
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.repository.DailyMoodRepository
import javax.inject.Inject

class DailyMoodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : DailyMoodRepository {

    override suspend fun saveDailyMood(entry: DailyMoodEntry) {
        localDataSource.save(entry)
    }

    override suspend fun getDailyMood(): Flow<List<DailyMoodEntry>> = localDataSource.getDayMoodEntry()

}