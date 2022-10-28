package com.example.test1.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.databinding.FragmentFavoriteBinding
import com.example.test1.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val mBinding get() = _binding!!
    private val viewModel by viewModels<FavoriteViewModel>()
    lateinit var newsAdapter : NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.list.observe(viewLifecycleOwner){
            newsAdapter.updateList(it)
        }
        newsAdapter.setonItemClikListener{
            val bundle = bundleOf("article" to it)
            view.findNavController().navigate(R.id.action_favoriteFragment_to_details_nav, bundle)
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        mBinding.rcFavorites.adapter = newsAdapter
        mBinding.rcFavorites.layoutManager = LinearLayoutManager(activity)
    }

}