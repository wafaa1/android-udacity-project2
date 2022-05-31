package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AstroidsDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository (private val database: AstroidsDatabase) {

    val videos: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAstroids()) {
            it.asDomainModel()
        }

//    suspend fun refreshVideos() {
//        withContext(Dispatchers.IO) {
//            val playlist = parseAsteroidsJsonResult()
//            database.asteroidDao.insertAll(*playlist.asDatabaseModel())
//        }
//    }
}
