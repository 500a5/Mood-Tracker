package soft.divan.moodtracker.feature.more.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import soft.divan.moodtracker.feature.more.domain.ThemeMode
import soft.divan.moodtracker.feature.more.domain.usecase.GetThemeModeUseCase
import soft.divan.moodtracker.feature.more.domain.usecase.SetThemeModeUseCase
import javax.inject.Inject

@HiltViewModel
class MoodSettingsViewModel @Inject constructor(
    private val getThemeModeUseCase: GetThemeModeUseCase,
    private val setThemeModeUseCase: SetThemeModeUseCase
) : ViewModel() {

    val themeMode = getThemeModeUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ThemeMode.SYSTEM
    )

    fun onThemeSelected(mode: ThemeMode) {
        viewModelScope.launch {
            setThemeModeUseCase(mode)
        }
    }
}
