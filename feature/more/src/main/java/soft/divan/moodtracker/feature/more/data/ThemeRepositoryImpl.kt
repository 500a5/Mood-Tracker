package soft.divan.moodtracker.feature.more.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import soft.divan.moodtracker.feature.more.domain.ThemeMode
import soft.divan.moodtracker.feature.more.domain.repositiry.ThemeRepository
import javax.inject.Inject


class ThemeRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    ThemeRepository {

    private val key = stringPreferencesKey("app_theme_mode")

    override fun getThemeMode(): Flow<ThemeMode> {
        return dataStore.data.map { prefs ->
            when (prefs[key]) {
                "LIGHT" -> ThemeMode.LIGHT
                "DARK" -> ThemeMode.DARK
                else -> ThemeMode.SYSTEM
            }
        }
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { prefs ->
            prefs[key] = mode.name
        }
    }
}
