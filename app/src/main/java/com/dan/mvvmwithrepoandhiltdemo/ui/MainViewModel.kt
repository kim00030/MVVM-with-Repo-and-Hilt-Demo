package com.dan.mvvmwithrepoandhiltdemo.ui

import androidx.lifecycle.*
import com.dan.mvvmwithrepoandhiltdemo.model.Blog
import com.dan.mvvmwithrepoandhiltdemo.repository.MainRepository
import com.dan.mvvmwithrepoandhiltdemo.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan Kim
 */

class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {

            when (mainStateEvent) {

                is MainStateEvent.GetVlogEvent -> {
                    mainRepository.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    /*no-option*/
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetVlogEvent : MainStateEvent()
    object None : MainStateEvent()
}