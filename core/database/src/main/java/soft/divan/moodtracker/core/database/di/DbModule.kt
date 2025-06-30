package soft.divan.moodtracker.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import soft.divan.moodtracker.core.database.DailyMoodRepositoryImpl
import soft.divan.moodtracker.core.database.MoodTrackerDatabase
import soft.divan.moodtracker.core.database.dao.DailyMoodDao
import soft.divan.moodtracker.core.database.datasource.LocalDataSource
import soft.divan.moodtracker.core.database.datasource.RoomLocalDataSource
import soft.divan.moodtracker.core.domain.repository.DailyMoodRepository

@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MoodTrackerDatabase = Room.databaseBuilder(context, MoodTrackerDatabase::class.java, "mood_tracker.db")
        .fallbackToDestructiveMigration(false)
        .build()

    @Provides
    fun provideDailyMoodDao(
        moodTrackerDatabase: MoodTrackerDatabase
    ):  DailyMoodDao = moodTrackerDatabase.dailyMoodDao()

    @Provides
     fun bindSaveDailyMoodEntryUseCase(impl: RoomLocalDataSource): LocalDataSource = impl

    @Provides
     fun bindDailyMoodRepository(impl: DailyMoodRepositoryImpl): DailyMoodRepository = impl
}