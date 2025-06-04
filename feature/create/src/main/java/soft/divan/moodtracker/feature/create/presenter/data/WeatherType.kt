package soft.divan.moodtracker.feature.create.presenter.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.model.WeatherType
import soft.divan.moodtracker.feature.create.R

data class WeatherType(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int,
)

fun WeatherType.mapToPresenter(): soft.divan.moodtracker.feature.create.presenter.data.WeatherType =
    when (this) {
        WeatherType.SUNNY -> WeatherType(R.string.weather_sunny_emoji, R.string.weather_sunny)
        WeatherType.CLOUDY -> WeatherType(R.string.weather_cloudy_emoji, R.string.weather_cloudy)
        WeatherType.RAINY -> WeatherType(R.string.weather_rainy_emoji, R.string.weather_rainy)
        WeatherType.SNOWY -> WeatherType(R.string.weather_snowy_emoji, R.string.weather_snowy)
        WeatherType.WINDY -> WeatherType(R.string.weather_windy_emoji, R.string.weather_windy)
        WeatherType.FOGGY -> WeatherType(R.string.weather_foggy_emoji, R.string.weather_foggy)
    }
