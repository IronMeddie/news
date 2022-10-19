package com.example.test1.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test1.data.api.TestRepo
import com.example.test1.models.NewsResp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TestRepo): ViewModel() {
    private val _all = MutableLiveData<NewsResp>()
    val all : LiveData<NewsResp>
    get() = _all

    init {
        getall()
    }

    fun getall() = viewModelScope.launch {
        repository.getAll().let{
            if (it.isSuccessful) _all.postValue(it.body()) else Log.d("checkData", "failed to load articles" + it.errorBody().toString())
        }
    }
}