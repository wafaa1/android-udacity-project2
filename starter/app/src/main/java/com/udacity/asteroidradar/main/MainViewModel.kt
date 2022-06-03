package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.AsteroidsApi
import com.udacity.asteroidradar.database.PictureOfDay
import com.udacity.asteroidradar.api.PictureApi
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : ViewModel() {
    enum class StatusValues { LOADING, ERROR, DONE }

    private val _podStatus = MutableLiveData<StatusValues>()
    val podStatus : LiveData<StatusValues>
        get() = _podStatus

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay : LiveData<PictureOfDay>
        get() = _pictureOfDay

    private val _asteroidsStatus = MutableLiveData<StatusValues>()
    val asteroidStatus : LiveData<StatusValues>
        get() = _asteroidsStatus

    private val _asteroidProperties = MutableLiveData<List<Asteroid>>()
    val asteroidProperties : LiveData<List<Asteroid>>
        get() = _asteroidProperties

    init{
        getPictureOfDay()
        getAsteroidProperties()
    }

    /**
     * Sets the value of the status LiveData to the Picture API status.
     */
    private fun getPictureOfDay() {
        viewModelScope.launch {
            _podStatus.value = StatusValues.LOADING
            try {
                _pictureOfDay.value = PictureApi.apod.getPictureOfDay()
                _podStatus.value = StatusValues.DONE
            } catch (e: Exception) {
                _podStatus.value = StatusValues.ERROR
            }
        }

    }

    private fun getAsteroidProperties() {
        viewModelScope.launch {
            _podStatus.value = StatusValues.LOADING
            try {
                _asteroidProperties.value = AsteroidsApi.asteroids.getAsteroids()
                _asteroidsStatus.value = StatusValues.DONE
            } catch (e: Exception) {
                _asteroidsStatus.value = StatusValues.ERROR
                _asteroidProperties.value = ArrayList()
            }
        }

    }



}

/*
class OverviewViewModel : ViewModel() {

    enum class MarsApiStatus { LOADING, ERROR, DONE }

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<MarsApiStatus>()
    // The external immutable LiveData for the request status String
    val status : LiveData<MarsApiStatus>
        get() = _status

    // The internal MutableLiveData list of MarsProperty that stores a MarsProperty
    private val _properties = MutableLiveData<List<MarsProperty>>()
    // The external immutable LiveData for a list of MarsProperty
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    // The internal MutableLiveData MarsProperty that stores the selected MarsProperty to navigate to its details page
    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    // external immutable LiveData for MarsProperty that stores the selected MarsProperty to navigate to its details page
    val navigateToSelectedProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
//        // TODO (05) Call the MarsApi to enqueue the Retrofit request, implementing the callbacks

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _properties.value = MarsApi.retrofitService.getProperties(filter.value)
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }

    }
}
 */