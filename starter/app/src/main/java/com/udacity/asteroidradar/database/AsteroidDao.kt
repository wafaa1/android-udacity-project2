package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDao {
    @Query("select * from asteroids_radar_table ORDER BY id DESC")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>

    //@Insert(onConflict= OnConflictStrategy.REPLACE)
    //fun insertAll(vararg asteroids: List<DatabaseAsteroid>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAsteroids(asteroids: List<DatabaseAsteroid>)

    //TODO delete all before today
    @Query("DELETE FROM asteroids_radar_table WHERE close_approach_date < :today")
    suspend fun clear(today: String)

    //Todo query for next seven days
    @Query("SELECT * FROM asteroids_radar_table WHERE close_approach_date BETWEEN :today AND :weekAfter ORDER BY close_approach_date ASC")
    fun getAsteroidsForWeek(today: String, weekAfter: String): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAsteroids(asteroids: List<Asteroid>)

}