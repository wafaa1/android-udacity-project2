package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseAsteroid::class], version = 1)
abstract class PictureOfDayDatabase : RoomDatabase() {
    abstract val pictureOfDayDao: PictureOfDayDao
}

private lateinit var INSTANCE: PictureOfDayDatabase

fun getPODDatabase(context: Context): PictureOfDayDatabase {
    synchronized(PictureOfDayDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                PictureOfDayDatabase::class.java,
                "picture_of_day_table").build()
        }
    }
    return INSTANCE
}