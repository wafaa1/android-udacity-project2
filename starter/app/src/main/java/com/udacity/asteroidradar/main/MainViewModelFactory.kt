package com.udacity.asteroidradar.main
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//
///**
// * Factory for constructing DevByteViewModel with parameter
// */
//class MainViewModelFactory(val app: Application) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return MainViewModel(app, MainViewModel.AsteroidFilter.SHOW_SAVED) as T
//        }
//        throw IllegalArgumentException("Unable to construct viewmodel")
//    }
//}