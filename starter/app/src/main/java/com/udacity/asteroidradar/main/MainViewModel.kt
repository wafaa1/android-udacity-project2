package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.getAsteroidsDatabase
import com.udacity.asteroidradar.database.getPODDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.repository.PODRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val filter: MutableLiveData<AsteroidFilter>) :
    AndroidViewModel(application) {

    enum class AsteroidFilter(val value: String) { SHOW_WEEK("week"), SHOW_TODAY("today"), SHOW_SAVED("saved") }

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

    val asteroidsList = Transformations.switchMap(filter) { filter ->
        when (filter) {
            AsteroidFilter.SHOW_TODAY -> asteroidsRepository.asteroidsToday
            AsteroidFilter.SHOW_WEEK -> asteroidsRepository.asteroidsOfWeek
            else -> {
                asteroidsRepository.asteroids
            }
        }
    }

    val pictureOfDay = podRepository.pictureOfDay

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

    fun updateFilter(filterToUpdate: AsteroidFilter) {
        filter.value = filterToUpdate
    }

    class MainViewModelFactory(val app: Application, val filter: MutableLiveData<AsteroidFilter>) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app, filter) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
