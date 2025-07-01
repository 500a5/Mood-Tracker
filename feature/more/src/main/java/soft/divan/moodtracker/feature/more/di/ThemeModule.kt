package soft.divan.moodtracker.feature.more.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import soft.divan.moodtracker.feature.more.data.ThemeRepositoryImpl
import soft.divan.moodtracker.feature.more.domain.repositiry.ThemeRepository
import soft.divan.moodtracker.feature.more.domain.usecase.GetThemeModeUseCase
import soft.divan.moodtracker.feature.more.domain.usecase.SetThemeModeUseCase
import soft.divan.moodtracker.feature.more.domain.usecase.impl.GetThemeModeUseCaseImpl
import soft.divan.moodtracker.feature.more.domain.usecase.impl.SetThemeModeUseCaseImpl


@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    private val Context.dataStore by preferencesDataStore("user_preferences")

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    fun provideThemePreferences(dataStore: DataStore<Preferences>): ThemeRepository {
        return ThemeRepositoryImpl(dataStore)
    }

    @Provides
    fun provideGetThemeUseCase(impl: GetThemeModeUseCaseImpl): GetThemeModeUseCase {
        return impl
    }

    @Provides
    fun provideSetThemeUseCase(impl: SetThemeModeUseCaseImpl): SetThemeModeUseCase {
        return impl
    }

}
