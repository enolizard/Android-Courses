package ru.gdgkazan.simpleweather.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gdgkazan.simpleweather.model.City

/**
 * @author Artur Vasilov
 */
interface WeatherService {

    @GET("data/2.5/weather?units=metric")
    fun getWeather(@Query("q") query: String): Call<City>

}
