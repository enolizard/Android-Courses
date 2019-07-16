package ru.gdgkazan.simpleweather.screen.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gdgkazan.simpleweather.R
import ru.gdgkazan.simpleweather.model.City
import java.util.*

/**
 * @author Artur Vasilov
 */
class CitiesAdapter(cities: List<City>, private val mOnItemClick: OnItemClick) : RecyclerView.Adapter<CityHolder>() {

    private val mCities: MutableList<City>

    private val mInternalListener = View.OnClickListener { view ->
        val city = view.tag as City
        mOnItemClick.onItemClick(city)
    }

    init {
        mCities = ArrayList(cities)
    }

    fun changeDataSet(cities: List<City>) {
        mCities.clear()
        mCities.addAll(cities)
        notifyDataSetChanged()
        notifyItemChanged(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_city, parent, false)
        return CityHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val city = mCities[position]
        holder.bind(city)
        holder.itemView.tag = city
        holder.itemView.setOnClickListener(mInternalListener)
    }

    override fun getItemCount(): Int {
        return mCities.size
    }

    interface OnItemClick {
        fun onItemClick(city: City)
    }
}
