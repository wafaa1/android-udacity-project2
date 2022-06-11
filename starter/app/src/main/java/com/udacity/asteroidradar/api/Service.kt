package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val myNasaApiKey="VCknWcf7SocNnq9cuk5J25r1CDvdrtMV9YzdDEKF"

//enum class AsteroidApiFilter(val value: String) { SHOW_WEEK("week"), SHOW_TODAY("today"), SHOW_SAVED("saved") }

// Picture of Day
interface PictureOfDayService {
    @GET(Constants.POD_ENDPOINT)
    suspend fun getPictureOfDay(@Query("api_key") apiKey: String = myNasaApiKey): PictureOfDay
}

// Asteroids
interface AsteroidsService {
    @GET(Constants.ASTEROIDS_ENDPOINT)
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String?,
        @Query("api_key") apiKey: String = myNasaApiKey
    ): String
//    ): Deferred<String>

}

// Moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object PictureApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val apod = retrofit.create(PictureOfDayService::class.java)
}

object AsteroidsApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val asteroids = retrofit.create(AsteroidsService::class.java)
}