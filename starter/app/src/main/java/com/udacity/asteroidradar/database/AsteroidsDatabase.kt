package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [DatabaseAsteroid::class], version = 1)
//abstract class AsteroidsDatabase : RoomDatabase() {
//    abstract val asteroidDao : AsteroidDao
//    companion object{
//        @Volatile
//        private var INSTANCE  : AsteroidsDatabase? = null
//
//        fun getInstance(context: Context) : AsteroidsDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AsteroidsDatabase::class.java,
//                        "asteroids_radar_table"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//}
@Database(entities = [DatabaseAsteroid::class], version = 1)
abstract class AsteroidsDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
}

private lateinit var INSTANCE: AsteroidsDatabase

fun getAsteroidsDatabase(context: Context): AsteroidsDatabase {
    synchronized(AsteroidsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AsteroidsDatabase::class.java,
                "asteroids_radar_table").build()
        }
    }
    return INSTANCE
}