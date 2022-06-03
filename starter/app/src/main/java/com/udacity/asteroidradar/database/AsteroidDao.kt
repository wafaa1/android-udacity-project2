package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDao {
    @Query("select * from asteroids_radar_table ORDER BY close_approach_date ASC")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids: DatabaseAsteroid)

    //delete all before today
    @Query("DELETE FROM asteroids_radar_table WHERE close_approach_date < :today")
    suspend fun clear(today: String)

    //query for next seven days
    @Query("SELECT * FROM asteroids_radar_table WHERE close_approach_date BETWEEN :startDay AND :weekAfter ORDER BY close_approach_date ASC")
    fun getAsteroidsForWeek(startDay: String, weekAfter: String): LiveData<List<DatabaseAsteroid>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAllAsteroids(asteroids: List<DatabaseAsteroid>)

/*
    @Query("SELECT * FROM asteroids_radar_table WHERE close_approach_date = :date")
    fun getAsteroidsForADay(date: String): LiveData<List<DatabaseAsteroid>>
 */
    //todo dao for pictureOfDay

}