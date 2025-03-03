package com.example.observatory.currentWeatherInfo.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.observatory.repository.ObservatoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentWeatherInfoViewModel(application: Application, private val repository: ObservatoryRepository): AndroidViewModel(application) {


    init {
        getCurrentWeatherInfo()
    }


    private fun getCurrentWeatherInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCurrentWeatherInfo()
            Log.d("response", "${response}")
        }
    }


}