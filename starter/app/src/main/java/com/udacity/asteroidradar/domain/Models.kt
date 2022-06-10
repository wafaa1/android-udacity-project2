package com.udacity.asteroidradar.domain

import android.net.Uri
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable {
}

@Parcelize
data class PictureOfDay(@Json(name = "media_type") val mediaType: String, val title: String,
                        val url: String) : Parcelable
