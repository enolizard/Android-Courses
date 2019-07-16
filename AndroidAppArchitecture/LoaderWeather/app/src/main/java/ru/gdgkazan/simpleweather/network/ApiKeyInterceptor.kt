package ru.gdgkazan.simpleweather.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.gdgkazan.simpleweather.BuildConfig
import java.io.IOException

/**
 * @author Artur Vasilov
 */
class ApiKeyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
                .addQueryParameter("appid", BuildConfig.API_KEY)
                .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
