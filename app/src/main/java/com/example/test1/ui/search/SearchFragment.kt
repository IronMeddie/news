package com.example.test1.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.databinding.FragmentMainBinding
import com.example.test1.databinding.FragmentSearchBinding
import com.example.test1.models.Article
import com.example.test1.models.Source
import com.example.test1.ui.adapters.NewsAdapter
import com.example.test1.ui.adapters.NewsViewHolder
import com.example.test1.ui.main.MainViewModel
import com.example.test1.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), NewsViewHolder.Liked {


    private var _binding: FragmentSearchBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var newsAdapterSearch: NewsAdapter
    private val viewmodel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        newsAdapterSearch.setonItemClikListener{
            val bundle = bundleOf("article" to it)
            view.findNavController().navigate(R.id.serach_to_details, bundle)
        }



        var job: Job? = null
        mBinding.searchEdit.addTextChangedListener { text->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                text.let {
                    if (it.toString().isNotEmpty()){
                        viewmodel.getSearchNews(query = it.toString())
                    }
                }
            }
        }


        viewmodel.searchLiveSata.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Succes -> {
                    mBinding.progressBarSearch.visibility = View.INVISIBLE
                    response.data?.let {
                        newsAdapterSearch.updateList(viewmodel.updateListLikes(it.articles))
                    }
                }
                is Resource.Error -> {
                    mBinding.progressBarSearch.visibility = View.INVISIBLE
                    response.data?.let {
                        Log.e("checkData", "MainFragment error: ${it}")
                    }
                }
                is Resource.Loading -> {
                    mBinding.progressBarSearch.visibility = View.VISIBLE
                }

            }

        }

    }

    private fun initAdapter() {
        newsAdapterSearch = NewsAdapter(this)
        mBinding.recyclerNewsSearch.adapter = newsAdapterSearch
        mBinding.recyclerNewsSearch.layoutManager = LinearLayoutManager(activity)


    }

    override fun onClickLiked(article: Article) {
        viewmodel.saveToFavorites(article)
        newsAdapterSearch.ItemLiked(article)
    }

}