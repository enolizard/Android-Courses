package ru.gdgkazan.simpleweather.model

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * @author Artur Vasilov
 */
class City(name: String) : Serializable {

    //    @SerializedName("name")
    @Json(name = "name")
    var name: String =name

    //    @SerializedName("weather")
    @Json(name = "weather")
    private val mWeathers: List<Weather>? = null

    //    @SerializedName("main")
    @Json(name = "main")
    val main: Main? = null

    //    @SerializedName("wind")
    @Json(name = "wind")
    val wind: Wind? = null

    val weather: Weather?
        get() = if (mWeathers == null || mWeathers.isEmpty()) {
            null
        } else mWeathers[0]
}
