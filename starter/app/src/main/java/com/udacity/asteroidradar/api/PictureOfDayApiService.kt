package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import com.udacity.asteroidradar.Constants.POD_ENDPOINT
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.database.PictureOfDay

//private const val BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=YOUR_API_KEY"

// Moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface PictureOfDayApiService {
        @GET(POD_ENDPOINT)//apod?api_key=VCknWcf7SocNnq9cuk5J25r1CDvdrtMV9YzdDEKE")
        suspend fun getPictureOfDay(): PictureOfDay
    }

object PictureApi {
    val retrofitService : PictureOfDayApiService by lazy {
        retrofit.create(PictureOfDayApiService::class.java)
    }
}