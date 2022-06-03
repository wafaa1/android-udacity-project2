package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.database.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import kotlinx.coroutines.Deferred
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.http.GET
//
///**
// * A retrofit service to fetch a devbyte playlist.
// */
//interface DevbyteService {
//    @GET("devbytes.json")
//    fun getPlaylist(): Deferred<NetworkVideoContainer>
//}
//
///**
// * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
// * full Kotlin compatibility.
// */
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
///**
// * Main entry point for network access. Call like `Network.devbytes.getPlaylist()`
// */
//object Network {
//    // Configure retrofit to parse JSON and use coroutines
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://devbytes.udacity.com/")
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .build()
//
//    val devbytes = retrofit.create(DevbyteService::class.java)
//}
private const val myNasaApiKey="VCknWcf7SocNnq9cuk5J25r1CDvdrtMV9YzdDEKF"

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
        @Query("api_key") apiKey: String = myNasaApiKey
    ): String
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
//object AsteroidApi {
//    val retrofitService : AsteroidApiService by lazy {
//        retrofit.create(AsteroidApiService::class.java)
//    }
//    val pictureOfTheDayService by lazy {
//        retrofit.create(PictureOfTheDayService::class.java)
//    }
//
//}