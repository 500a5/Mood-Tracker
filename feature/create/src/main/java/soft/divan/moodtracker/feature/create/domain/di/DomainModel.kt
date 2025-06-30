package soft.divan.moodtracker.feature.create.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import soft.divan.moodtracker.core.domain.usecase.SaveDailyMoodEntryUseCase
import soft.divan.moodtracker.core.domain.usecase.impl.SaveDailyMoodEntryUseCaseImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindSaveDailyMoodEntryUseCase(impl: SaveDailyMoodEntryUseCaseImpl): SaveDailyMoodEntryUseCase

}