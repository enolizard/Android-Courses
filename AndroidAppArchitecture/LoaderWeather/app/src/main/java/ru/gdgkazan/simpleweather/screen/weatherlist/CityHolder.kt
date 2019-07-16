package ru.gdgkazan.simpleweather.screen.weatherlist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gdgkazan.simpleweather.R
import ru.gdgkazan.simpleweather.model.City

/**
 * @author Artur Vasilov
 */
class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mCityName: TextView
    private val mTemperature: TextView

    init {
        mCityName = itemView.findViewById(R.id.city_name)
        mTemperature = itemView.findViewById(R.id.temperature)
    }

    fun bind(city: City) {
        mCityName.text = city.name
        if (city.main != null) {
            val temp = mTemperature.resources.getString(R.string.f_temperature, city.main.temp)
            mTemperature.text = temp
        }
    }
}
