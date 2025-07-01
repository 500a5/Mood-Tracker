package soft.divan.moodtracker.feature.more.domain.repositiry

import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.feature.more.domain.ThemeMode

interface ThemeRepository {
    fun getThemeMode(): Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
}
