package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=YOUR_API_KEY"
private const val IMAGE_URL = "https://api.nasa.gov/planetary/apod?api_key=VCknWcf7SocNnq9cuk5J25r1CDvdrtMV9YzdDEKF"

// Moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create()) // will use moshi instead to get kotlin objects instead of strings here
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(IMAGE_URL)
        .build()

interface PictureOfDayApiService {
        @GET("picOfDay")
        suspend fun getPictureOfDay(): PictureOfDay
    }

object PictureApi {
    val retrofitService : PictureOfDayApiService by lazy {
        retrofit.create(PictureOfDayApiService::class.java)
    }
}
