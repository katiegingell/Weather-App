package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.CurrentConditions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val apiService: ApiService) :
    ViewModel() {
    private val _currentConditions: MutableLiveData<CurrentConditions> = MutableLiveData()
    val currentConditions: LiveData<CurrentConditions>
        get() = _currentConditions
    val textField: MutableLiveData<String> = MutableLiveData("55106")
    val invalidZip: MutableLiveData<Boolean> = MutableLiveData(false)

    fun validateZipCode(): Boolean {
        val userZip = textField.value
        return if (
            (userZip.isNullOrEmpty() || userZip.length != 5) || (userZip.any { !it.isDigit() }))
            false
        else {
            viewAppeared()
            true
        }
    }

    fun viewAppeared(zip: String? = textField.value) = viewModelScope.launch {
        _currentConditions.value = apiService.getWeatherData(zip.toString())
    }
}