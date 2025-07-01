package soft.divan.moodtracker.feature.more.domain.usecase.impl

import soft.divan.moodtracker.feature.more.domain.ThemeMode
import soft.divan.moodtracker.feature.more.domain.repositiry.ThemeRepository
import soft.divan.moodtracker.feature.more.domain.usecase.SetThemeModeUseCase
import javax.inject.Inject

class SetThemeModeUseCaseImpl @Inject constructor(
    private val themeRepository: ThemeRepository
) : SetThemeModeUseCase {
    override suspend operator fun invoke(mode: ThemeMode) {
        themeRepository.setThemeMode(mode)
    }
}