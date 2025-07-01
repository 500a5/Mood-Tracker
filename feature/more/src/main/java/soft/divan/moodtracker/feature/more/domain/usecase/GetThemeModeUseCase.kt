package soft.divan.moodtracker.feature.more.domain.usecase

import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.feature.more.domain.ThemeMode

interface GetThemeModeUseCase {
    operator fun invoke(): Flow<ThemeMode>
}