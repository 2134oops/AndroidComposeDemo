package com.rc.facebook.ui.screen.facebookList.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FacebookListViewModel(application: Application) :
    AndroidViewModel(application = application) {

    private val _isRefreshingState: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }

    val isRefreshingState: StateFlow<Boolean> by lazy {
        _isRefreshingState.asStateFlow()
    }


    init {
        fetchFacebookList(false)
    }


    private fun fetchFacebookList(isRefresh: Boolean) {
//
    }


    fun onRefresh() {
        _isRefreshingState.value = true
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            fetchFacebookList(true)
            withContext(Dispatchers.Main) {
                _isRefreshingState.value = false
            }
        }
    }

}