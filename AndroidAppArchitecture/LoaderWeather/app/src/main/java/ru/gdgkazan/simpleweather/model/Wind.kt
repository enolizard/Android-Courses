package ru.gdgkazan.simpleweather.model

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * @author Artur Vasilov
 */
class Wind : Serializable {

    @Json(name = "speed")
    private val mSpeed: Double = 0.toDouble()

    val speed: Int
        get() = mSpeed.toInt()
}
