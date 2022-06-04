package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureOfDayDao {
    @Query("SELECT * FROM picture_of_day_table WHERE date = :date LIMIT 1")
    fun getPicOfDay(date: String) : LiveData<DatabasePOD>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg pictureOfDay: DatabasePOD)

    @Query("DELETE FROM picture_of_day_table WHERE date < :date")
    suspend fun clearOldPOD(date: String)
}