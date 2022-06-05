package com.udacity.asteroidradar.database

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.udacity.asteroidradar.api.getTodayDate
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "asteroids_radar_table")
data class DatabaseAsteroid constructor (
    @PrimaryKey//(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "codename")
    val codename: String,
    @ColumnInfo(name = "close_approach_date")
    val closeApproachDate: String,
    @ColumnInfo(name = "absolute_magnitude")
    val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimated_diameter")
    val estimatedDiameter: Double,
   @ColumnInfo(name = "relative_velocity")
    val relativeVelocity: Double,
    @ColumnInfo(name = "distance_from_earth")
    val distanceFromEarth: Double,
    @ColumnInfo(name = "is_potentially_hazardous")
    val isPotentiallyHazardous: Boolean
    ) : Parcelable

@Parcelize
@Entity(tableName = "picture_of_day_table")
data class DatabasePOD constructor (
//    @Json(name = "media_type")
    @ColumnInfo(name= "media_type")
    val mediaType: String,
    val title: String = "White Cosmos",
    @PrimaryKey
    val url: String,
    val date: String
) : Parcelable

/**
 * converts from database objects to domain objects
 */
fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun DatabasePOD.asDomainModel() : PictureOfDay {
    return DatabasePOD (
        mediaType = this.mediaType,
        title = this.title,
        url = this.url)
}



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
//
