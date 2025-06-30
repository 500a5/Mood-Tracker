package soft.divan.moodtracker.feature.create.domain.repository


import soft.divan.moodtracker.core.model.DailyMoodEntry

interface DailyMoodRepository {
    suspend fun saveDailyMood(entry: DailyMoodEntry)
}
