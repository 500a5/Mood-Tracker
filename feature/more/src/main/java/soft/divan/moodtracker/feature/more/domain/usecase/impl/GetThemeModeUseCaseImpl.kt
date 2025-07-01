package soft.divan.moodtracker.feature.more.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import soft.divan.moodtracker.feature.more.domain.ThemeMode
import soft.divan.moodtracker.feature.more.domain.repositiry.ThemeRepository
import soft.divan.moodtracker.feature.more.domain.usecase.GetThemeModeUseCase
import javax.inject.Inject

class GetThemeModeUseCaseImpl @Inject constructor(
    private val themeRepository: ThemeRepository
) : GetThemeModeUseCase {
    override operator fun invoke(): Flow<ThemeMode> {
        return themeRepository.getThemeMode()
    }
}