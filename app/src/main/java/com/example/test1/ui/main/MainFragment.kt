package com.example.test1.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.MainActivity
import com.example.test1.R
import com.example.test1.databinding.FragmentMainBinding
import com.example.test1.models.Article
import com.example.test1.ui.adapters.HolderView.Companion.HEADER
import com.example.test1.ui.adapters.NewsAdapter
import com.example.test1.ui.adapters.NewsViewHolder
import com.example.test1.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), NewsViewHolder.Liked { // ntcn

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    private val viewmodel by viewModels<MainViewModel>()
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (activity as MainActivity).getBottomMenu()

        initAdapter()

        newsAdapter.setonItemClikListener{
            val bundle = bundleOf("article" to it)
            view.findNavController().navigate(R.id.action_mainFragment_to_details_nav, bundle)
        }

        viewmodel.newsLiveSata.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Succes -> {
                    mBinding.progressBar.visibility = View.INVISIBLE
                    response.data?.let {
                        newsAdapter.updateListAddHeader(viewmodel.updateListLikes(it.articles) , HEADER)
                    }
                }
                is Resource.Error -> {
                    mBinding.progressBar.visibility = View.INVISIBLE
                    response.data?.let {
                        Log.e("checkData", "MainFragment error: ${it}")
                    }
                }
                is Resource.Loading -> {
                    mBinding.progressBar.visibility = View.VISIBLE
                }

            }

        }


    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter(this)
        mBinding.recyclerNews.adapter = newsAdapter
        mBinding.recyclerNews.layoutManager = LinearLayoutManager(activity)
    }

    override fun onClickLiked(article: Article) {
        viewmodel.saveToFavorites(article)
        newsAdapter.ItemLiked(article)
    }


}