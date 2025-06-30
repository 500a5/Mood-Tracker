package soft.divan.moodtracker.feature.create.domain.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import soft.divan.moodtracker.feature.create.data.repository.DailyMoodRepositoryImpl
import soft.divan.moodtracker.feature.create.domain.repository.DailyMoodRepository
import soft.divan.moodtracker.feature.create.domain.usecase.SaveDailyMoodEntryUseCase
import soft.divan.moodtracker.feature.create.domain.usecase.SaveDailyMoodEntryUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindSaveDailyMoodEntryUseCase(impl: SaveDailyMoodEntryUseCaseImpl): SaveDailyMoodEntryUseCase

    @Binds
    abstract fun bindDailyMoodRepository(impl: DailyMoodRepositoryImpl): DailyMoodRepository
}