package ru.gdgkazan.simpleweather.screen.weather

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import ru.gdgkazan.simpleweather.model.City
import ru.gdgkazan.simpleweather.network.ApiFactory
import java.io.IOException

class WeatherLoader(context: Context, private val mCityName: String) : AsyncTaskLoader<City>(context) {

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): City? {
        try {
            return ApiFactory.weatherService.getWeather(mCityName).execute().body()
        } catch (e: IOException) {
            return null
        }

    }
}


