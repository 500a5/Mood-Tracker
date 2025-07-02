package soft.divan.moodtracker.feature.entries.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import soft.divan.moodtracker.core.domain.usecase.GetDailyMoodEntersUseCase
import soft.divan.moodtracker.feature.entries.model.DailyMoodEntriesUiState
import javax.inject.Inject

@HiltViewModel
class MoodEntriesViewModel @Inject constructor(
    private val getDailyMoodEntersUseCase: GetDailyMoodEntersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DailyMoodEntriesUiState>(DailyMoodEntriesUiState.Loading)
    val uiState: StateFlow<DailyMoodEntriesUiState> = _uiState.asStateFlow()
        .onStart { loadDailyMoodEnters() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            DailyMoodEntriesUiState.Loading
        )

    fun loadDailyMoodEnters() {
        viewModelScope.launch {
            _uiState.update {
                DailyMoodEntriesUiState.Success(
                    getDailyMoodEntersUseCase.invoke().first()
                )
            }
        }
    }

}
