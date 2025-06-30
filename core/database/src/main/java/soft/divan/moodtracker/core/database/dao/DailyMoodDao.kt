package soft.divan.moodtracker.core.database.dao


import androidx.room.Dao
import androidx.room.Insert
import soft.divan.moodtracker.core.database.entity.DailyMoodEntity
import soft.divan.moodtracker.core.database.entity.EmotionEntity
import soft.divan.moodtracker.core.database.entity.HabitEntity
import soft.divan.moodtracker.core.database.entity.HealthEntity
import soft.divan.moodtracker.core.database.entity.HobbyEntity
import soft.divan.moodtracker.core.database.entity.NutritionEntity
import soft.divan.moodtracker.core.database.entity.WeatherEntity


@Dao
interface DailyMoodDao {

    @Insert
    suspend fun insertEntry(entry: DailyMoodEntity)

    @Insert
    suspend fun insertEmotions(emotions: List<EmotionEntity>)

    @Insert
    suspend fun insertHabits(habits: List<HabitEntity>)

    @Insert
    suspend fun insertNutrition(nutritions: List<NutritionEntity>)

    @Insert
    suspend fun insertHobbies(hobbies: List<HobbyEntity>)

    @Insert
    suspend fun insertHealth(healthStates: List<HealthEntity>)

    @Insert
    suspend fun insertWeather(weather: WeatherEntity)
}
