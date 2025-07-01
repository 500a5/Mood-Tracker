package soft.divan.moodtracker

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.model.DayMoodRating
import soft.divan.moodtracker.core.domain.model.EmotionCategory
import soft.divan.moodtracker.core.domain.model.HabitType
import soft.divan.moodtracker.core.domain.model.HealthState
import soft.divan.moodtracker.core.domain.model.HobbyCategory
import soft.divan.moodtracker.core.domain.model.NutritionQuality
import soft.divan.moodtracker.core.domain.model.SleepQuality
import soft.divan.moodtracker.core.domain.model.WeatherType
import soft.divan.moodtracker.core.domain.repository.DailyMoodRepository
import soft.divan.moodtracker.core.domain.usecase.impl.SaveDailyMoodEntryUseCaseImpl
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class SaveDailyMoodEntryUseCaseImplTest {

    @Mock
    private lateinit var repository: DailyMoodRepository

    private lateinit var useCase: SaveDailyMoodEntryUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SaveDailyMoodEntryUseCaseImpl(repository)
    }

    @Test
    fun `invoke should call repository saveDailyMood with correct entry`() = runTest {
        val entry = DailyMoodEntry(
            id = "test-id",
            date = LocalDate.of(2025, 6, 30),
            moodRating = DayMoodRating.GOOD,
            emotions = listOf(EmotionCategory.HAPPY, EmotionCategory.CALM),
            sleep = SleepQuality(durationInHours = 8, wentToBedLate = false),
            nutrition = listOf(NutritionQuality.HEALTHY),
            hobbies = listOf(HobbyCategory.SPORTS),
            health = listOf(HealthState.HEALTHY),
            weather = WeatherType.SUNNY,
            habits = listOf(HabitType.EXERCISE),
            note = "Felt great today"
        )

        useCase.invoke(entry)

        verify(repository, times(1)).saveDailyMood(entry)
    }

    @Test(expected = RuntimeException::class)
    fun `invoke should throw when repository throws`() = runTest {
        val entry = DailyMoodEntry()
        `when`(repository.saveDailyMood(entry)).thenThrow(RuntimeException("DB error"))

        useCase.invoke(entry)
    }
}
