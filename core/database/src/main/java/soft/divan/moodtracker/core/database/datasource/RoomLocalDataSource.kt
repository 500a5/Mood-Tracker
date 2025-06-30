package soft.divan.moodtracker.core.database.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import soft.divan.moodtracker.core.database.dao.DailyMoodDao
import soft.divan.moodtracker.core.database.entity.DailyMoodEntity
import soft.divan.moodtracker.core.database.entity.EmotionEntity
import soft.divan.moodtracker.core.database.entity.HabitEntity
import soft.divan.moodtracker.core.database.entity.HealthEntity
import soft.divan.moodtracker.core.database.entity.HobbyEntity
import soft.divan.moodtracker.core.database.entity.NutritionEntity
import soft.divan.moodtracker.core.database.entity.WeatherEntity
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.model.DayMoodRating
import soft.divan.moodtracker.core.domain.model.EmotionCategory
import soft.divan.moodtracker.core.domain.model.HabitType
import soft.divan.moodtracker.core.domain.model.HealthState
import soft.divan.moodtracker.core.domain.model.HobbyCategory
import soft.divan.moodtracker.core.domain.model.NutritionQuality
import soft.divan.moodtracker.core.domain.model.WeatherType
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(
    private val dao: DailyMoodDao
) : LocalDataSource {

    override suspend fun save(entry: DailyMoodEntry) {
        dao.insertEntry(DailyMoodEntity.fromDomain(entry))
        val moodId = entry.id
        dao.insertEmotions(entry.emotions.map { EmotionEntity(moodId = moodId, emotion = it.name) })
        dao.insertHabits(entry.habits.map { HabitEntity(moodId = moodId, habit = it.name) })
        dao.insertNutrition(entry.nutrition.map {
            NutritionEntity(
                moodId = moodId,
                nutrition = it.name
            )
        })
        dao.insertHobbies(entry.hobbies.map { HobbyEntity(moodId = moodId, hobby = it.name) })
        dao.insertHealth(entry.health.map { HealthEntity(moodId = moodId, healthState = it.name) })
        entry.weather?.let {
            dao.insertWeather(WeatherEntity(moodId = moodId, weather = it.name))
        }
    }

    override suspend fun getDayMoodEntry(): Flow<List<DailyMoodEntry>>  = dao.getAllMoodEntries()
        .map { list ->
            list.map { entity ->
                runBlocking {
                    entity.toDomain(
                        emotions = dao.getEmotionsForMood(entity.id),
                        habits = dao.getHabitsForMood(entity.id),
                        nutrition = dao.getNutritionForMood(entity.id),
                        hobbies = dao.getHobbiesForMood(entity.id),
                        health = dao.getHealthForMood(entity.id),
                        weather = dao.getWeatherForMood(entity.id)
                    )
                }
            }
        }


}

fun DailyMoodEntity.toDomain(
    emotions: List<EmotionEntity>,
    habits: List<HabitEntity>,
    nutrition: List<NutritionEntity>,
    hobbies: List<HobbyEntity>,
    health: List<HealthEntity>,
    weather: WeatherEntity?
): DailyMoodEntry = DailyMoodEntry(
    id = id,
    moodRating = DayMoodRating.valueOf(moodRating),
    emotions = emotions.map { EmotionCategory.valueOf(it.emotion) },
    habits = habits.map { HabitType.valueOf(it.habit) },
    nutrition = nutrition.map { NutritionQuality.valueOf(it.nutrition) },
    hobbies = hobbies.map { HobbyCategory.valueOf(it.hobby) },
    health = health.map { HealthState.valueOf(it.healthState) },
    weather = weather?.let { WeatherType.valueOf(it.weather) },
    date = date
)