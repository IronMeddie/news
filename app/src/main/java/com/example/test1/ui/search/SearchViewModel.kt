package com.example.test1.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test1.data.api.NewsRepository
import com.example.test1.models.Article
import com.example.test1.models.NewsResp
import com.example.test1.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    var searchLiveSata: MutableLiveData<Resource<NewsResp>> = MutableLiveData()
    var searchPage = 1


    fun getSearchNews(query: String){
        viewModelScope.launch {
            searchLiveSata.postValue(Resource.Loading())
            val response = repository.getSearchNews(query,searchPage)
            if (response.isSuccessful){
                response.body().let {
                    searchLiveSata.postValue(Resource.Succes(it))
                }
            }else searchLiveSata.postValue(Resource.Error(response.message()))
        }
    }


    fun saveToFavorites(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        if (!repository.alreadiLiked(article)) {
            repository.addToFavorites(article)
        } else repository.deliteFavorite(article)
    }

    fun updateListLikes(articles: MutableList<Article>): MutableList<Article> {
        viewModelScope.launch(Dispatchers.IO) {
            articles.forEach{
                it.liked = repository.alreadiLiked(it)
            }
        }
        return articles
    }

}