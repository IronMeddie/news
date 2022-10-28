package com.example.test1.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test1.data.api.NewsRepository
import com.example.test1.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    private val favoritesData : MutableLiveData<List<Article>> = MutableLiveData()
    val list : LiveData<List<Article>>
    get() = favoritesData

    init {
        getList()
    }


    fun getList() = viewModelScope.launch(Dispatchers.IO) {
        favoritesData.postValue(repository.getFavorites())
    }


}