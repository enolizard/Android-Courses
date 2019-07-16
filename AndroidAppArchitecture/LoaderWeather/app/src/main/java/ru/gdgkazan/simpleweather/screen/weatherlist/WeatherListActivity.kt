package ru.gdgkazan.simpleweather.screen.weatherlist

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_weather_list.*
import ru.gdgkazan.simpleweather.R
import ru.gdgkazan.simpleweather.model.City
import ru.gdgkazan.simpleweather.screen.general.LoadingDialog
import ru.gdgkazan.simpleweather.screen.general.LoadingView
import ru.gdgkazan.simpleweather.screen.general.SimpleDividerItemDecoration
import ru.gdgkazan.simpleweather.screen.weather.WeatherActivity
import ru.gdgkazan.simpleweather.screen.weather.WeatherLoader
import java.util.*

class WeatherListActivity : AppCompatActivity(), CitiesAdapter.OnItemClick, SwipeRefreshLayout.OnRefreshListener {

    private val mToolbar: Toolbar by lazy { toolbar }
    private val mRecyclerView: RecyclerView by lazy { recyclerView }
    private val mSwipeRefreshLayout: SwipeRefreshLayout by lazy { swipeContainer }

    private var mAdapter: CitiesAdapter? = null
    private var mLoadingView: LoadingView? = null

    private val loadedCity = ArrayList<City>()
    private lateinit var cities: List<City>

    private val initialCities: List<City>
        get() {
            val cities = ArrayList<City>()
            val initialCities = resources.getStringArray(R.array.initial_cities)
            for (city in initialCities) {
                cities.add(City(city))
            }
            return cities
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        setSupportActionBar(mToolbar)

        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.addItemDecoration(SimpleDividerItemDecoration(this, false))
        mAdapter = CitiesAdapter(initialCities, this)
        mRecyclerView!!.adapter = mAdapter
        mLoadingView = LoadingDialog.view(supportFragmentManager)

        mSwipeRefreshLayout!!.setOnRefreshListener(this)

        mSwipeRefreshLayout!!.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN)

        cities = initialCities
        load(cities!!, false)
    }


    override fun onRefresh() {
        load(cities!!, true)
        Handler().postDelayed({ mSwipeRefreshLayout!!.isRefreshing = false }, 4000)
    }


    private inner class WeatherCallbacks(private val city: City, private val cityName: String) :
        LoaderManager.LoaderCallbacks<City> {

        override fun onCreateLoader(id: Int, args: Bundle?): Loader<City> {
            return WeatherLoader(this@WeatherListActivity, cityName)
        }

        override fun onLoadFinished(loader: Loader<City>, data: City?) {
            showWeather(data)
        }

        override fun onLoaderReset(loader: Loader<City>) {

        }
    }

    private fun loadWeather(restart: Boolean, city: City, cityName: String, id: Int?) {
        mLoadingView!!.showLoadingIndicator()
        val callbacks = WeatherCallbacks(city, cityName)

        if (restart) {
            supportLoaderManager.restartLoader(id!!, Bundle.EMPTY, callbacks)
        } else {
            supportLoaderManager.initLoader(id!!, Bundle.EMPTY, callbacks)
        }
    }

    private fun load(cities: List<City>, restart: Boolean) {
        for (i in cities.indices) {
            val cityName = cities[i].name
            loadWeather(restart, cities[i], cityName, i)
        }
    }

    private fun showWeather(city: City?) {
        if (city == null || city.main == null
            || city.weather == null || city.wind == null
        ) {
            showError()
            return
        }

        loadedCity.add(city)
        if (loadedCity.size >= cities!!.size) {
            mLoadingView!!.hideLoadingIndicator()
            sortAllCities(loadedCity)
            mAdapter!!.changeDataSet(loadedCity)
            loadedCity.clear()
        }
    }

    private fun sortAllCities(cities: List<City>): List<City> {
        Collections.sort(cities) { t0, t1 -> t0.name.compareTo(t1.name) }

        return cities
    }

    override fun onItemClick(city: City) {
        startActivity(WeatherActivity.makeIntent(this, city.name))
    }

    private fun showError() {
        mLoadingView!!.hideLoadingIndicator()
        Snackbar.make(mRecyclerView!!, "Error loading weather", Snackbar.LENGTH_LONG)
            .setAction("Retry") { v -> load(cities!!, true) }
            .setDuration(4000)
            .show()
    }
}
