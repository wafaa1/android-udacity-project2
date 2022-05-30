package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.database.PictureOfDay
import com.udacity.asteroidradar.api.PictureApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    enum class PictureOfDayStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<PictureOfDayStatus>()
    val status : LiveData<PictureOfDayStatus>
        get() = _status

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay : LiveData<PictureOfDay>
        get() = _pictureOfDay

    init{
        getPictureOfDay()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getPictureOfDay() {
        viewModelScope.launch {
            _status.value = PictureOfDayStatus.LOADING
            try {
                _pictureOfDay.value = PictureApi.retrofitService.getPictureOfDay()
                _status.value = PictureOfDayStatus.DONE
            } catch (e: Exception) {
                _status.value = PictureOfDayStatus.ERROR
//                _pictureOfDay.value =
            }
        }

    }

}