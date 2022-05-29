package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroids_radar_table")
data class Asteroids (

    @PrimaryKey
    val id: Long = 0L,
    @ColumnInfo(name="name")
    val codename: String,
    @ColumnInfo(name="absolute_magnitude_h")
    val absoluteMagnitude: Double,
    @ColumnInfo(name="estimated_diameter")
    val estimatedDiameter: EstimatedDiameter,
    @ColumnInfo(name="close_approach_data")
    val closeApproachDataList: ArrayList<CloseApproachData>,
    @ColumnInfo(name="relative_velocity")
    val relativeVelocity: RelativeVelocity,
    @ColumnInfo(name="miss_distance")
    val distanceFromEarth: DistanceFromEarth,
    @ColumnInfo(name="is_potentially_hazardous_asteroid")
    val isPotentiallyHazardous: Boolean )

data class EstimatedDiameter(
    val kilometers: Kilometers
)

data class Kilometers(
    val estimatedDiameterMax: Double
)

data class CloseApproachData(
    val relativeVelocity: RelativeVelocity
)

data class RelativeVelocity(
    @ColumnInfo(name="kilometers_per_second")
    val kilometersPerSecond: Double
)

data class DistanceFromEarth(
    val astronomical: Double
)

//(id, codename, formattedDate, absoluteMagnitude,
//estimatedDiameter, relativeVelocity, distanceFromEarth, isPotentiallyHazardous)

/*
In the above JSON object, the asteroids are inside near_earth_objects key and all the asteroids for a given date are all listed there.
For this project you will use the following fields:
id (Not for displaying but for using in db)
absolute_magnitude
estimated_diameter_max (Kilometers)
is_potentially_hazardous_asteroid
close_approach_data -> relative_velocity -> kilometers_per_second
close_approach_data -> miss_distance -> astronomical
*/

//
//    /**
//     * converts from database objects to domain objects
//     */
//    fun List<DatabaseAstroids>.asDomainModel(): List<Asteroids> {
//        return map {
//            Asteroids(
//                url = it.url,
//                title = it.title,
//                description = it.description,
//                updated = it.updated,
//                thumbnail = it.thumbnail
//            )
//        }
//    }

