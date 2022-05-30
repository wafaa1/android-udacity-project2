package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AstroidDao {
    @Query("select * from databaseAstroid")
    fun getAstroids(): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertAll(vararg astroids: DatabaseAsteroid)
}