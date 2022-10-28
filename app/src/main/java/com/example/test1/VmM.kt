package com.example.test1

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VmM : ViewModel(){
    private val _state : MutableLiveData<Int> = MutableLiveData()
    val state : LiveData<Int>
    get() = _state


    init {
        getVisible()
    }

    private val _statesplash : MutableLiveData<Int> = MutableLiveData()
    val splash : LiveData<Int>
        get() = _statesplash


    private fun getVisible() = viewModelScope.launch {
        delay(2000)
        _state.postValue(View.VISIBLE)
        _statesplash.postValue(View.GONE)
    }


}