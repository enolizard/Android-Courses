package ru.gdgkazan.simpleweather.screen.weather

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.weather_card.*
import ru.gdgkazan.simpleweather.R
import ru.gdgkazan.simpleweather.model.City
import ru.gdgkazan.simpleweather.screen.general.LoadingDialog
import ru.gdgkazan.simpleweather.screen.general.LoadingView

class WeatherActivity : AppCompatActivity() {

    private val mToolbar: Toolbar by lazy { toolbar }
    private val mToolbarTitle: TextView by lazy { toolbar_title }
    private val mWeatherLayout: View by lazy { weather_layout }
    private val mWeatherMain: TextView by lazy { weather_main }
    private val mTemperature: TextView by lazy { temperature }
    private val mPressure: TextView by lazy { pressure }
    internal val mHumidity: TextView by lazy { humidity }
    internal val mWindSpeed: TextView by lazy { wind_speed }
    internal val mErrorLayout: TextView by lazy { error_layout }

    private var mLoadingView: LoadingView? = null

    private var mCityName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(mToolbar)
        if (supportActionBar != null) {
            supportActionBar!!.title = ""
        }
        mCityName = intent.getStringExtra(CITY_NAME_KEY)
        mToolbarTitle!!.text = mCityName

        mLoadingView = LoadingDialog.view(supportFragmentManager)
        loadWeather(false)

        mErrorLayout.setOnClickListener { loadWeather(true) }
    }

//    @OnClick(R.id.error_layout)
//    fun onErrorLayoutClick() {
//        loadWeather(true)
//    }

    private fun loadWeather(restart: Boolean) {
        mWeatherLayout!!.visibility = View.INVISIBLE
        mErrorLayout!!.visibility = View.GONE
        mLoadingView!!.showLoadingIndicator()
        val callbacks = WeatherCallbacks()
        if (restart) {
            supportLoaderManager.restartLoader(R.id.weather_loader_id, Bundle.EMPTY, callbacks)
        } else {
            supportLoaderManager.initLoader(R.id.weather_loader_id, Bundle.EMPTY, callbacks)
        }
    }

    private fun showWeather(city: City?) {
        if (city == null || city.main == null || city.weather == null
            || city.wind == null
        ) {
            showError()
            return
        }
        mLoadingView!!.hideLoadingIndicator()

        mWeatherLayout!!.visibility = View.VISIBLE
        mErrorLayout!!.visibility = View.GONE

        mToolbarTitle!!.text = city.name
        mWeatherMain!!.text = city.weather!!.main
        mTemperature!!.text = getString(R.string.f_temperature, city.main!!.temp)
        mPressure!!.text = getString(R.string.f_pressure, city.main!!.pressure)
        mHumidity!!.text = getString(R.string.f_humidity, city.main!!.humidity)
        mWindSpeed!!.text = getString(R.string.f_wind_speed, city.wind!!.speed)
    }

    private fun showError() {
        mLoadingView!!.hideLoadingIndicator()
        mWeatherLayout!!.visibility = View.INVISIBLE
        mErrorLayout!!.visibility = View.VISIBLE
    }

    private inner class WeatherCallbacks : LoaderManager.LoaderCallbacks<City> {

        override fun onCreateLoader(id: Int, args: Bundle?): Loader<City> {
            return RetrofitWeatherLoader(this@WeatherActivity, mCityName!!)
        }

        override fun onLoadFinished(loader: Loader<City>, city: City) {
            showWeather(city)
        }

        override fun onLoaderReset(loader: Loader<City>) {
            // Do nothing
        }
    }

    companion object {

        private val CITY_NAME_KEY = "city_name"

        fun makeIntent(activity: Activity, cityName: String): Intent {
            val intent = Intent(activity, WeatherActivity::class.java)
            intent.putExtra(CITY_NAME_KEY, cityName)
            return intent
        }
    }
}
