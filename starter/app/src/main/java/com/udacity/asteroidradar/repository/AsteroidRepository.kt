package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository (private val database: AsteroidsDatabase) {

    private val startTodayDate = getTodayDate()
    private val endAfter7Days = getDefaultEndDate()

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it?.asDomainModel()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroidsList =
                parseAsteroidsJsonResult(JSONObject(
                    AsteroidsApi
                        .asteroids
                        .getAsteroids(startTodayDate, endAfter7Days).await()))
            database
                .asteroidDao.insertAll(*asteroidsList.asDatabaseModel())
        }
    }

    suspend fun deleteOldAsteroids() {
        withContext(Dispatchers.IO) {
            database.asteroidDao.clear(getTodayDate())
        }

    }
}