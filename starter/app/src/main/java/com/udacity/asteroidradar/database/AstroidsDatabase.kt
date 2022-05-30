package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseAsteroid::class], version = 1)
abstract class AstroidsDatabase : RoomDatabase() {
    abstract val astroidDao : AstroidDao
    companion object{
        @Volatile
        private var INSTANCE  : AstroidsDatabase? = null

        fun getInstance(context: Context) : AstroidsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AstroidsDatabase::class.java,
                        "asteroids_radar_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}