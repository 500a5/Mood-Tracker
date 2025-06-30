package soft.divan.moodtracker.feature.create.data.datasource

import soft.divan.moodtracker.core.model.DailyMoodEntry

interface LocalDataSource {
    suspend fun save(entry: DailyMoodEntry)
}
