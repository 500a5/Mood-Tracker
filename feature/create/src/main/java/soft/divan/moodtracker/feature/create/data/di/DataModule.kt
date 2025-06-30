package soft.divan.moodtracker.feature.create.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import soft.divan.moodtracker.feature.create.data.datasource.LocalDataSource
import soft.divan.moodtracker.feature.create.data.datasource.RoomLocalDataSource
import soft.divan.moodtracker.feature.create.domain.usecase.SaveDailyMoodEntryUseCase
import soft.divan.moodtracker.feature.create.domain.usecase.SaveDailyMoodEntryUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindSaveDailyMoodEntryUseCase(impl: RoomLocalDataSource): LocalDataSource


}