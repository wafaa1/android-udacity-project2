package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository (private val database: AsteroidsDatabase) {

    val videos: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }

//    suspend fun refreshVideos() {
//        withContext(Dispatchers.IO) {
//            val playlist = parseAsteroidsJsonResult()
//            database.asteroidDao.insertAll(*playlist.asDatabaseModel())
//        }
//    }
}
