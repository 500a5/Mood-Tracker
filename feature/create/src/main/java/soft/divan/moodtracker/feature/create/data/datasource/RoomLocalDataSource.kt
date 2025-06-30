package soft.divan.moodtracker.feature.create.data.datasource

import soft.divan.moodtracker.core.database.dao.DailyMoodDao
import soft.divan.moodtracker.core.database.entity.DailyMoodEntity
import soft.divan.moodtracker.core.database.entity.EmotionEntity
import soft.divan.moodtracker.core.database.entity.HabitEntity
import soft.divan.moodtracker.core.database.entity.HealthEntity
import soft.divan.moodtracker.core.database.entity.HobbyEntity
import soft.divan.moodtracker.core.database.entity.NutritionEntity
import soft.divan.moodtracker.core.database.entity.WeatherEntity
import soft.divan.moodtracker.core.model.DailyMoodEntry
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(
    private val dao: DailyMoodDao
) : LocalDataSource {

    override suspend fun save(entry: DailyMoodEntry) {
        dao.insertEntry(DailyMoodEntity.fromDomain(entry))
        val moodId = entry.id
        dao.insertEmotions(entry.emotions.map { EmotionEntity(moodId= moodId, emotion = it.name) })
        dao.insertHabits(entry.habits.map { HabitEntity(moodId= moodId, habit = it.name) })
        dao.insertNutrition(entry.nutrition.map { NutritionEntity(moodId= moodId, nutrition =  it.name) })
        dao.insertHobbies(entry.hobbies.map { HobbyEntity(moodId= moodId, hobby = it.name) })
        dao.insertHealth(entry.health.map { HealthEntity(moodId= moodId, healthState = it.name) })
        entry.weather?.let {
            dao.insertWeather(WeatherEntity(moodId= moodId, weather = it.name))
        }
    }
}

