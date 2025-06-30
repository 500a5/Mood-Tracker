package soft.divan.moodtracker.core.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
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

    @Query("SELECT * FROM daily_mood ORDER BY date DESC")
    fun getAllMoodEntries(): Flow<List<DailyMoodEntity>>

    @Query("SELECT * FROM emotions WHERE moodId = :moodId")
    suspend fun getEmotionsForMood(moodId: String): List<EmotionEntity>

    @Query("SELECT * FROM habits WHERE moodId = :moodId")
    suspend fun getHabitsForMood(moodId: String): List<HabitEntity>

    @Query("SELECT * FROM nutrition WHERE moodId = :moodId")
    suspend fun getNutritionForMood(moodId: String): List<NutritionEntity>

    @Query("SELECT * FROM hobbies WHERE moodId = :moodId")
    suspend fun getHobbiesForMood(moodId: String): List<HobbyEntity>

    @Query("SELECT * FROM health WHERE moodId = :moodId")
    suspend fun getHealthForMood(moodId: String): List<HealthEntity>

    @Query("SELECT * FROM weather WHERE moodId = :moodId")
    suspend fun getWeatherForMood(moodId: String): WeatherEntity?

}
