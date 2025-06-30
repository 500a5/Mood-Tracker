package soft.divan.moodtracker.feature.create.data.repository

import soft.divan.moodtracker.core.model.DailyMoodEntry
import soft.divan.moodtracker.feature.create.data.datasource.LocalDataSource
import soft.divan.moodtracker.feature.create.domain.repository.DailyMoodRepository
import javax.inject.Inject

class DailyMoodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : DailyMoodRepository {

    override suspend fun saveDailyMood(entry: DailyMoodEntry) {
        localDataSource.save(entry)
    }
}
