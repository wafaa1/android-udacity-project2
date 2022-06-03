//package com.udacity.asteroidradar.api
//
//import androidx.lifecycle.Transformations.map
//import com.squareup.moshi.JsonClass
//import com.udacity.asteroidradar.Asteroid
//import com.udacity.asteroidradar.database.DatabaseAsteroid
//
//@JsonClass(generateAdapter = true)
//data class NetworkAsteroidContainer(val videos: List<NetworkAsteroid>)
//
//@JsonClass(generateAdapter = true)
//data class NetworkAsteroid(
//    val id: Long,
//    val codename: String,
//    val closeApproachDate: String,
//    val absoluteMagnitude: Double,
//    val estimatedDiameter: Double,
//    val relativeVelocity: Double,
//    val distanceFromEarth: Double,
//    val isPotentiallyHazardous: Boolean)
//
///**
// * Convert Network results to database objects
// */
//fun NetworkAsteroidContainer.asDomainModel(): List<DatabaseAsteroid> {
//    return map {
//        Asteroid(
//            id = it.id,
//            codename = it.codename,
//            closeApproachDate = it.closeApproachDate,
//            absoluteMagnitude = it.absoluteMagnitude,
//            estimatedDiameter = it.estimatedDiameter,
//            relativeVelocity = it.relativeVelocity,
//            distanceFromEarth = it.distanceFromEarth,
//            isPotentiallyHazardous = it.isPotentiallyHazardous)
//    }
//}
///**
// * converts from data transfer objects to database objects
// */
//fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo> {
//    return videos.map {
//        DatabaseVideo (
//            title = it.title,
//            description = it.description,
//            url = it.url,
//            updated = it.updated,
//            thumbnail = it.thumbnail)
//    }.toTypedArray()
//}
//
///**
// * converts from database objects to domain objects
// */
//fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid> {
//    return map {
//        Asteroid(
//            id = it.id,
//            codename = it.codename,
//            closeApproachDate = it.closeApproachDate,
//            absoluteMagnitude = it.absoluteMagnitude,
//            estimatedDiameter = it.estimatedDiameter,
//            relativeVelocity = it.relativeVelocity,
//            distanceFromEarth = it.distanceFromEarth,
//            isPotentiallyHazardous = it.isPotentiallyHazardous
//        )
//    }
//}
//
//fun List<Asteroid>.asDatabaseModel(): Array<DatabaseAsteroid> {
//    return map {
//        DatabaseAsteroid(
//            id = it.id,
//            codename = it.codename,
//            closeApproachDate = it.closeApproachDate,
//            absoluteMagnitude = it.absoluteMagnitude,
//            estimatedDiameter = it.estimatedDiameter,
//            relativeVelocity = it.relativeVelocity,
//            distanceFromEarth = it.distanceFromEarth,
//            isPotentiallyHazardous = it.isPotentiallyHazardous)
//    }.toTypedArray()
//}
