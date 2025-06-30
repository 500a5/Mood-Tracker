package soft.divan.moodtracker.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import soft.divan.moodtracker.core.database.converters.LocalDateConverter
import soft.divan.moodtracker.core.database.dao.DailyMoodDao
import soft.divan.moodtracker.core.database.entity.DailyMoodEntity
import soft.divan.moodtracker.core.database.entity.EmotionEntity
import soft.divan.moodtracker.core.database.entity.HabitEntity
import soft.divan.moodtracker.core.database.entity.HealthEntity
import soft.divan.moodtracker.core.database.entity.HobbyEntity
import soft.divan.moodtracker.core.database.entity.NutritionEntity
import soft.divan.moodtracker.core.database.entity.WeatherEntity

@Database(
    entities = [
        DailyMoodEntity::class,
        EmotionEntity::class,
        HabitEntity::class,
        NutritionEntity::class,
        HobbyEntity::class,
        HealthEntity::class,
        WeatherEntity::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(LocalDateConverter::class)
abstract class MoodTrackerDatabase : RoomDatabase() {
    abstract fun dailyMoodDao(): DailyMoodDao
}