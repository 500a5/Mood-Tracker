package soft.divan.moodtracker

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
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
import soft.divan.moodtracker.core.domain.usecase.impl.GetDailyMoodEntersUseCaseImpl
import java.time.LocalDate
import java.util.UUID

@OptIn(ExperimentalCoroutinesApi::class)
class GetDailyMoodEntersUseCaseImplTest {

    @Mock
    private lateinit var repository: DailyMoodRepository

    private lateinit var useCase: GetDailyMoodEntersUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetDailyMoodEntersUseCaseImpl(repository)
    }

    @Test
    fun `invoke should return detailed daily mood entries from repository`() = runTest {

        val mockEntry1 = DailyMoodEntry(
            id = UUID.randomUUID().toString(),
            date = LocalDate.of(2025, 6, 29),
            moodRating = DayMoodRating.NORMAL,
            emotions = listOf(EmotionCategory.SAD, EmotionCategory.ANXIOUS),
            sleep = SleepQuality(
                durationInHours = 5,
                wentToBedLate = true,
                wokeUpOften = true,
                wokeUpTired = true
            ),
            nutrition = listOf(NutritionQuality.JUNK_FOOD, NutritionQuality.SKIPPED_MEAL),
            hobbies = listOf(HobbyCategory.MUSIC, HobbyCategory.GAMES),
            health = listOf(HealthState.HEADACHE, HealthState.FATIGUE),
            weather = WeatherType.CLOUDY,
            habits = listOf(HabitType.CAFFEINE, HabitType.SCREEN_TIME),
            note = "Felt stressed due to exams"
        )

        val mockEntry2 = DailyMoodEntry(
            id = UUID.randomUUID().toString(),
            date = LocalDate.of(2025, 6, 30),
            moodRating = DayMoodRating.AMAZING,
            emotions = listOf(EmotionCategory.GRATEFUL, EmotionCategory.HAPPY),
            sleep = SleepQuality(
                durationInHours = 8,
                wentToBedLate = false,
                wokeUpOften = false,
                wokeUpTired = false
            ),
            nutrition = listOf(NutritionQuality.HEALTHY),
            hobbies = listOf(HobbyCategory.SPORTS),
            health = listOf(HealthState.HEALTHY),
            weather = WeatherType.SUNNY,
            habits = listOf(HabitType.EXERCISE),
            note = "Great productive day"
        )

        val mockData = listOf(mockEntry1, mockEntry2)
        `when`(repository.getDailyMood()).thenReturn(flowOf(mockData))


        val result = useCase.invoke().first()


        verify(repository).getDailyMood()
        assertEquals(2, result.size)


        with(result[0]) {
            assertEquals(mockEntry1.id, id)
            assertEquals(LocalDate.of(2025, 6, 29), date)
            assertEquals(DayMoodRating.NORMAL, moodRating)
            assertEquals(listOf(EmotionCategory.SAD, EmotionCategory.ANXIOUS), emotions)
            assertEquals(5, sleep.durationInHours)
            assertTrue(sleep.wentToBedLate)
            assertTrue(sleep.wokeUpOften)
            assertTrue(sleep.wokeUpTired)
            assertEquals(listOf(NutritionQuality.JUNK_FOOD, NutritionQuality.SKIPPED_MEAL), nutrition)
            assertEquals(listOf(HobbyCategory.MUSIC, HobbyCategory.GAMES), hobbies)
            assertEquals(listOf(HealthState.HEADACHE, HealthState.FATIGUE), health)
            assertEquals(WeatherType.CLOUDY, weather)
            assertEquals(listOf(HabitType.CAFFEINE, HabitType.SCREEN_TIME), habits)
            assertEquals("Felt stressed due to exams", note)
        }


        with(result[1]) {
            assertEquals(mockEntry2.id, id)
            assertEquals(LocalDate.of(2025, 6, 30), date)
            assertEquals(DayMoodRating.AMAZING, moodRating)
            assertEquals(listOf(EmotionCategory.GRATEFUL, EmotionCategory.HAPPY), emotions)
            assertEquals(8, sleep.durationInHours)
            assertFalse(sleep.wentToBedLate)
            assertFalse(sleep.wokeUpOften)
            assertFalse(sleep.wokeUpTired)
            assertEquals(listOf(NutritionQuality.HEALTHY), nutrition)
            assertEquals(listOf(HobbyCategory.SPORTS), hobbies)
            assertEquals(listOf(HealthState.HEALTHY), health)
            assertEquals(WeatherType.SUNNY, weather)
            assertEquals(listOf(HabitType.EXERCISE), habits)
            assertEquals("Great productive day", note)
        }
    }

    @Test
    fun `invoke should return empty list when repository returns no entries`() = runTest {

        `when`(repository.getDailyMood()).thenReturn(flowOf(emptyList()))

        val result = useCase.invoke().first()


        verify(repository).getDailyMood()
        assertTrue("Expected empty result list", result.isEmpty())
    }

    @Test(expected = RuntimeException::class)
    fun `invoke should throw exception when repository throws`() = runTest {

        val exception = RuntimeException("Database failure")
        `when`(repository.getDailyMood()).thenReturn(flow {
            throw exception
        })

        useCase.invoke().first() // Should throw

    }



}