package soft.divan.moodtracker.feature.entries

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import soft.divan.moodtracker.core.domain.usecase.GetDailyMoodEntersUseCase
import soft.divan.moodtracker.core.domain.usecase.impl.GetDailyMoodEntersUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class Domain3Mod3ule {

    @Binds
    abstract fun bindGetDailyMoodEntersUseCase(impl: GetDailyMoodEntersUseCaseImpl): GetDailyMoodEntersUseCase

}