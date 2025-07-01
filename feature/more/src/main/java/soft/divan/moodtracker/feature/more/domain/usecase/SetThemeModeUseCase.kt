package soft.divan.moodtracker.feature.more.domain.usecase

import soft.divan.moodtracker.feature.more.domain.ThemeMode

interface SetThemeModeUseCase {
    suspend operator fun invoke(mode: ThemeMode)
}