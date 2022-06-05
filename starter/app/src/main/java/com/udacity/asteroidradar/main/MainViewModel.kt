package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.getAsteroidsDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val asteroidsDatabase = getAsteroidsDatabase(application)
    private val asteroidsRepository = AsteroidRepository(asteroidsDatabase)

    init {
        viewModelScope.launch {
            asteroidsRepository.refreshAsteroids()
        }
    }

    val asteroidsList = asteroidsRepository.asteroids
}


//using above code that uses repo instead
//class MainViewModel(app: Application) : ViewModel() {
//    enum class StatusValues { LOADING, ERROR, DONE }
//
//    private val _podStatus = MutableLiveData<StatusValues>()
//    val podStatus : LiveData<StatusValues>
//        get() = _podStatus
//
//    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
//    val pictureOfDay : LiveData<PictureOfDay>
//        get() = _pictureOfDay
//
//    private val _asteroidsStatus = MutableLiveData<StatusValues>()
//    val asteroidStatus : LiveData<StatusValues>
//        get() = _asteroidsStatus
//
//    private val _asteroidProperties = MutableLiveData<List<Asteroid>>()
//    val asteroidProperties : LiveData<List<Asteroid>>
//        get() = _asteroidProperties
//
//    init{
//        getPictureOfDay()
//        getAsteroidProperties()
//    }
//
//    /**
//     * Sets the value of the status LiveData to the Picture API status.
//     */
//    private fun getPictureOfDay() {
//        viewModelScope.launch {
//            _podStatus.value = StatusValues.LOADING
//            try {
//                _pictureOfDay.value = PictureApi.apod.getPictureOfDay() //todo save pod in db and get from repo
//                _podStatus.value = StatusValues.DONE
//            } catch (e: Exception) {
//                _podStatus.value = StatusValues.ERROR
//            }
//        }
//    }
//
//    private fun getAsteroidProperties() {
//        viewModelScope.launch {
//            _asteroidsStatus.value = StatusValues.LOADING
//            try {
//                _asteroidProperties.value = parseAsteroidsJsonResult(AsteroidsApi.asteroids.getAsteroids())
//                _asteroidsStatus.value = StatusValues.DONE
//            } catch (e: Exception) {
//                _asteroidsStatus.value = StatusValues.ERROR //e.message
//                _asteroidProperties.value = ArrayList()
//            }
//        }
//    }
//}
//
