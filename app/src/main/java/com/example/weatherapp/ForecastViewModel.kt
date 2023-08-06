package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.Forecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _forecast: MutableLiveData<Forecast> = MutableLiveData()
    val forecast: LiveData<Forecast>
        get() = _forecast

    fun validateZipCode(userZip: String): Boolean {
        return if (
            (userZip.isNullOrEmpty() || userZip.length != 5) || (userZip.any { !it.isDigit() }))
            false
        else {
            viewAppeared(userZip)
            true
        }
    }

    fun viewAppeared(zip: String) = viewModelScope.launch {
        _forecast.value = apiService.getForecastData(zip = zip)
    }
}