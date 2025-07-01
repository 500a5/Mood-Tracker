package soft.divan.moodtracker

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import soft.divan.moodtracker.core.domain.model.DayMoodRating
import soft.divan.moodtracker.core.domain.model.EmotionCategory
import soft.divan.moodtracker.core.domain.model.HabitType
import soft.divan.moodtracker.core.domain.model.HealthState
import soft.divan.moodtracker.core.domain.model.HobbyCategory
import soft.divan.moodtracker.core.domain.model.NutritionQuality
import soft.divan.moodtracker.core.domain.model.SleepQuality
import soft.divan.moodtracker.core.domain.model.WeatherType
import soft.divan.moodtracker.core.domain.usecase.SaveDailyMoodEntryUseCase
import soft.divan.moodtracker.feature.create.presenter.CreateMoodViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class CreateMoodViewModelTest {

    @Mock
    private lateinit var saveUseCase: SaveDailyMoodEntryUseCase

    private lateinit var viewModel: CreateMoodViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = CreateMoodViewModel(saveUseCase)
    }

    @Test
    fun `updateMood should update uiState with new mood`() = runBlocking {
        val mood = DayMoodRating.GOOD
        viewModel.updateMood(mood)
        val state = viewModel.uiState.value
        assertEquals(mood, state.moodRating)
    }

    @Test
    fun `toggleEmotion should add and remove emotion`() = runBlocking {
        val emotion = EmotionCategory.SAD


        viewModel.toggleEmotion(emotion)
        assertTrue(viewModel.uiState.value.emotions.contains(emotion))


        viewModel.toggleEmotion(emotion)
        assertFalse(viewModel.uiState.value.emotions.contains(emotion))
    }

    @Test
    fun `updateNote should update note in uiState`() = runBlocking {
        val note = "Very productive day"
        viewModel.updateNote(note)
        assertEquals(note, viewModel.uiState.value.note)
    }

    @Test
    fun `toggleHabit should add and remove habit`() = runTest {
        val habit = HabitType.MEDITATION

        viewModel.toggleHabit(habit)
        assertTrue(viewModel.uiState.value.habits.contains(habit))

        viewModel.toggleHabit(habit)
        assertFalse(viewModel.uiState.value.habits.contains(habit))
    }

    @Test
    fun `saveDay should invoke saveDailyMoodEntryUseCase with current state`() = runBlocking {
        val mood = DayMoodRating.GOOD
        viewModel.updateMood(mood)
        val expected = viewModel.uiState.value

        viewModel.saveDay()

        verify(saveUseCase).invoke(expected)
    }


    @Test
    fun `updateSleepQuality should update sleep in uiState`() = runBlocking {
        val sleepQuality = SleepQuality()
        viewModel.updateSleepQuality(sleepQuality)
        assertEquals(sleepQuality, viewModel.uiState.value.sleep)
    }

    @Test
    fun `toggleNutrition should add and remove nutrition quality`() = runBlocking {
        val nutrition = NutritionQuality.HEALTHY

        viewModel.toggleNutrition(nutrition)
        assertTrue(viewModel.uiState.value.nutrition.contains(nutrition))

        viewModel.toggleNutrition(nutrition)
        assertFalse(viewModel.uiState.value.nutrition.contains(nutrition))
    }

    @Test
    fun `toggleHealthState should add and remove health state`() = runBlocking {
        val state = HealthState.HEADACHE

        viewModel.toggleHealthState(state)
        assertTrue(viewModel.uiState.value.health.contains(state))

        viewModel.toggleHealthState(state)
        assertFalse(viewModel.uiState.value.health.contains(state))
    }

    @Test
    fun `toggleHobby should add and remove hobby`() = runBlocking {
        val hobby = HobbyCategory.READING

        viewModel.toggleHobby(hobby)
        assertTrue(viewModel.uiState.value.hobbies.contains(hobby))

        viewModel.toggleHobby(hobby)
        assertFalse(viewModel.uiState.value.hobbies.contains(hobby))
    }

    @Test
    fun `updateWeather should update weather in uiState`() = runBlocking {
        val weather = WeatherType.RAINY
        viewModel.updateWeather(weather)
        assertEquals(weather, viewModel.uiState.value.weather)
    }

    @Test
    fun `updateNote with empty string should update note in uiState`() = runBlocking {
        val note = ""
        viewModel.updateNote(note)
        assertEquals(note, viewModel.uiState.value.note)
    }

    @Test
    fun `saveDay should catch exception if use case throws and not crash`() = runBlocking {

        val entry = viewModel.uiState.value
        doThrow(RuntimeException("DB error")).`when`(saveUseCase).invoke(entry)

        try {
            viewModel.saveDay()
        } catch (e: Exception) {
            fail("Exception should have been handled inside ViewModel if try-catch was implemented")
        }
    }

    @Test
    fun `updateMood emits correct state in flow`() = runBlocking {
        viewModel.uiState.test {
            val initial = awaitItem()
            viewModel.updateMood(DayMoodRating.BAD)
            val updated = awaitItem()
            assertEquals(DayMoodRating.BAD, updated.moodRating)
            cancelAndIgnoreRemainingEvents()
        }
    }

}
