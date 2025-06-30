package soft.divan.moodtracker.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "hobbies")
data class HobbyEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val moodId: String,
    val hobby: String,
)