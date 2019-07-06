package by.enolizard.keddit.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("/top.json")
    fun getTop(
        @Query(value = "after") after: String,
        @Query(value = "limit") limit: String
    ): Call<RedditNewsResponse>
}
