package ru.gdgkazan.simpleweather.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.gdgkazan.simpleweather.BuildConfig


object ApiFactory {

    private var sClient: OkHttpClient? = null

    @Volatile
    private var sService: WeatherService? = null

    val weatherService: WeatherService
        get() {
            var service = sService
            if (service == null) {
                synchronized(ApiFactory::class.java) {
                    service = sService
                    if (service == null) {
                        sService = buildRetrofit().create<WeatherService>(WeatherService::class.java!!)
                        service = sService
                    }
                }
            }
            return service!!
        }

    private val client: OkHttpClient
        get() {
            var client = sClient
            if (client == null) {
                synchronized(ApiFactory::class.java) {
                    client = sClient
                    if (client == null) {
                        sClient = buildClient()
                        client = sClient
                    }
                }
            }
            return client!!
        }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .addInterceptor(ApiKeyInterceptor())
                .build()
    }
}
