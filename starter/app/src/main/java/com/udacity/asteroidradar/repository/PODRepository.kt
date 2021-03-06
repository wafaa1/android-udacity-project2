package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.database.PictureOfDayDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PODRepository(private val database: PictureOfDayDatabase) {
    private val startTodayDate = getTodayDate()
    private val endAfter7Days = getDefaultEndDate()
    val pictureOfDay: LiveData<PictureOfDay> = Transformations
        .map(database.pictureOfDayDao.getPicOfDay(getTodayDate())) {
            it?.asDomainModel()
        }
    suspend fun refreshPictureOfDay() {
        withContext(Dispatchers.IO) {
            val pic = PictureApi.apod.getPictureOfDay()
            database
                .pictureOfDayDao.insertAll(pic.asDatabaseModel())
        }
    }

    suspend fun deleteOldPOD() {
        withContext(Dispatchers.IO) {
            database.pictureOfDayDao.clearOldPOD(getTodayDate())
        }

    }
}