package soft.divan.moodtracker.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import java.time.LocalDate
import java.util.UUID


@Entity(tableName = "daily_mood")
data class DailyMoodEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val date: LocalDate,
    val moodRating: String,
    val note: String
) {
    companion object {
        fun fromDomain(entry: DailyMoodEntry): DailyMoodEntity {
            return DailyMoodEntity(
                id = entry.id,
                date = entry.date,
                moodRating = entry.moodRating.name,
                note = entry.note
            )
        }
    }
}



