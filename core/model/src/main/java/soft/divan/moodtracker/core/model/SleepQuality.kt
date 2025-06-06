package soft.divan.moodtracker.core.model

// Категория сна
data class SleepQuality(
    val durationInHours: Int = 7,
    val wentToBedLate: Boolean = false,
    val wokeUpOften: Boolean = false,
    val wokeUpTired: Boolean = false
)