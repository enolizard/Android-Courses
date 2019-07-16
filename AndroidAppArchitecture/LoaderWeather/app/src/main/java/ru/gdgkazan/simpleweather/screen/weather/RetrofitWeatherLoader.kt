package ru.gdgkazan.simpleweather.screen.weather

import android.content.Context
import androidx.loader.content.Loader

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gdgkazan.simpleweather.model.City
import ru.gdgkazan.simpleweather.network.ApiFactory

/**
 * @author Artur Vasilov
 */
class RetrofitWeatherLoader(context: Context, cityName: String) : Loader<City>(context) {

    private val mCall: Call<City>

    private var mCity: City? = null

    init {
        mCall = ApiFactory.weatherService.getWeather(cityName)
    }

    override fun onStartLoading() {
        super.onStartLoading()
        if (mCity != null) {
            deliverResult(mCity)
        } else {
            forceLoad()
        }
    }

    override fun onForceLoad() {
        super.onForceLoad()
        mCall.enqueue(object : Callback<City> {
            override fun onResponse(call: Call<City>, response: Response<City>) {
                mCity = response.body()
                deliverResult(mCity)
            }

            override fun onFailure(call: Call<City>, t: Throwable) {
                deliverResult(null)
            }
        })
    }

    override fun onStopLoading() {
        mCall.cancel()
        super.onStopLoading()
    }
}

