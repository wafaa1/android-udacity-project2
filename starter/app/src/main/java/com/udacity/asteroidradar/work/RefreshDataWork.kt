package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.getAsteroidsDatabase
import com.udacity.asteroidradar.database.getPODDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.repository.PODRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val asteroidDatabase = getAsteroidsDatabase(applicationContext)
        val asteroidRepository = AsteroidRepository(asteroidDatabase)

        val pictureOfDayDatabase = getPODDatabase(applicationContext)
        val podRepository = PODRepository(pictureOfDayDatabase)

        return try {
            asteroidRepository.refreshAsteroids()
            podRepository.refreshPictureOfDay()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}