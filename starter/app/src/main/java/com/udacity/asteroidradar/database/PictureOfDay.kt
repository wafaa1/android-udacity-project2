package com.udacity.asteroidradar.database

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureOfDay(@Json(name = "media_type") val mediaType: String, val title: String,
                        val url: String) : Parcelable
//todo dbEntity to store pic of day and required conversions asDomain, asDatabase