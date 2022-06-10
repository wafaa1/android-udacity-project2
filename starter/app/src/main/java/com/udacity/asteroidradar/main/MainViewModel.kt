package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.getAsteroidsDatabase
import com.udacity.asteroidradar.database.getPODDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.repository.PODRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()
    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid

    private val asteroidsDatabase = getAsteroidsDatabase(application)
    private val asteroidsRepository = AsteroidRepository(asteroidsDatabase)

    private val podDatabase = getPODDatabase(application)
    private val podRepository = PODRepository(podDatabase)

    init {
        viewModelScope.launch {
            asteroidsRepository.refreshAsteroids()
            podRepository.refreshPictureOfDay()
        }
    }

    val asteroidsList = asteroidsRepository.asteroids
    val pictureOfDay = podRepository.pictureOfDay

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

//    fun updateFilter(filter: AsteroidFilter) {
//        getMarsRealEstateProperties(filter)
//    }


    class MainViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
