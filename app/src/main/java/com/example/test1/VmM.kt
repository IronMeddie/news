package com.example.test1

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class VmM : ViewModel(){
    private val _state : MutableLiveData<Int> = MutableLiveData()
    val state : LiveData<Int>
    get() = _state


    fun getVisible(){
        _state.postValue(View.VISIBLE)
    }

}