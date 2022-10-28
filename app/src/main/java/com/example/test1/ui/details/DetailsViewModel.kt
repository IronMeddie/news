package com.example.test1.ui.details

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
class DetailsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val favorited: MutableLiveData<Boolean> = MutableLiveData(false)
    val fav :LiveData<Boolean>
    get() = favorited

    fun getSavedArticles() = viewModelScope.launch(Dispatchers.IO){
        repository.getFavorites()
    }

    fun saveToFavorites(article: Article) = viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavorites(article)
    }

    fun delete(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        repository.deliteFavorite(article)
    }
}