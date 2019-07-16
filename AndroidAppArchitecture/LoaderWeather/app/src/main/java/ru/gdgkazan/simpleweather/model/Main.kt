package ru.gdgkazan.simpleweather.model

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * @author Artur Vasilov
 */
class Main : Serializable {

    @Json(name = "temp")
    private val mTemp: Double = 0.toDouble()

    @Json(name = "pressure")
    private val mPressure: Double = 0.toDouble()

    @Json(name = "humidity")
    private val mHumidity: Double = 0.toDouble()

    val temp: Int
        get() = mTemp.toInt()

    val pressure: Int
        get() = mPressure.toInt()

    val humidity: Int
        get() = mHumidity.toInt()
}
