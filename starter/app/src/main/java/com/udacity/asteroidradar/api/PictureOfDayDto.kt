package com.udacity.asteroidradar.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.database.DatabasePOD
import com.udacity.asteroidradar.domain.PictureOfDay


@JsonClass(generateAdapter = true)
data class NetworkPODContainer(val picOfDay: NetworkPOD)

@JsonClass(generateAdapter = true)
data class NetworkPOD(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String)

///**
// * Convert Network results to domain objects
// */
//fun NetworkPODContainer.asDomainModel(): PictureOfDay {
//    return picOfDay.map {
//        PictureOfDay(
//            mediaType = it.mediaType,
//            title = it.title,
//            url = it.url
//        )
//    }
//}
/**
 * Convert Network results to domain objects
 */
fun NetworkPODContainer.asDomainModel(): PictureOfDay {
    return PictureOfDay(
        mediaType = picOfDay.mediaType,
        title = picOfDay.title,
        url = picOfDay.url
    )
}
///**
// * Convert Network results to database objects
// */
//fun NetworkPODContainer.asDatabaseModel(): DatabasePOD {
//    return picOfDay.map {
//        DatabasePOD(
//            mediaType = it.mediaType,
//            title = it.title,
//            url = it.url,
//            date = getTodayDate()
//        )
//    }
//}
/**
 * Convert Network results to database objects
 */
fun NetworkPODContainer.asDatabaseModel(): DatabasePOD {
    return DatabasePOD(
        mediaType = picOfDay.mediaType,
        title = picOfDay.title,
        url = picOfDay.url,
        date = getTodayDate()
    )
}