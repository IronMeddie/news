package com.example.test1.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test1.data.api.NewsRepository
import com.example.test1.models.NewsResp
import com.example.test1.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    var newsLiveSata: MutableLiveData<Resource<NewsResp>> = MutableLiveData()
    var newsPage = 1

    private fun getNews(countryCode: String) = viewModelScope.launch {
        newsLiveSata.postValue(Resource.Loading())
        val response = newsRepository.getNews(countryCode = countryCode, pageNumber = newsPage)

        if (response.isSuccessful){
            response.body().let { res->
                newsLiveSata.postValue(Resource.Succes(res))
            }

        }else newsLiveSata.postValue(Resource.Error(message = response.message()))
    }


init {
    getNews("ru")
}




}

