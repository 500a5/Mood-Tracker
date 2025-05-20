package soft.divan.moodtracker.core.model

// Категория сна
data class SleepQuality(
    val durationInHours: Int,
    val wentToBedLate: Boolean,
    val wokeUpOften: Boolean,
    val wokeUpTired: Boolean
)