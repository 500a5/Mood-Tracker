package soft.divan.data_ui.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.data_ui.R
import soft.divan.moodtracker.core.domain.model.WeatherType

data class WeatherType(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int,
)

fun WeatherType.mapToPresenter(): soft.divan.data_ui.data.WeatherType =
    when (this) {
        WeatherType.SUNNY -> WeatherType(R.string.weather_sunny_emoji, R.string.weather_sunny)
        WeatherType.CLOUDY -> WeatherType(R.string.weather_cloudy_emoji, R.string.weather_cloudy)
        WeatherType.RAINY -> WeatherType(R.string.weather_rainy_emoji, R.string.weather_rainy)
        WeatherType.SNOWY -> WeatherType(R.string.weather_snowy_emoji, R.string.weather_snowy)
        WeatherType.WINDY -> WeatherType(R.string.weather_windy_emoji, R.string.weather_windy)
        WeatherType.FOGGY -> WeatherType(R.string.weather_foggy_emoji, R.string.weather_foggy)
    }
