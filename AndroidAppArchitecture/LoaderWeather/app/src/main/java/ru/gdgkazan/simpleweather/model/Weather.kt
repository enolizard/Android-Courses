package ru.gdgkazan.simpleweather.model

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * @author Artur Vasilov
 */
class Weather : Serializable {

    @Json(name = "main")
    var main: String? = null

    @Json(name = "icon")
    var icon: String? = null
}
