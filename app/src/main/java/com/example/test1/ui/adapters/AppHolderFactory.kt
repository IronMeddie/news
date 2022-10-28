package com.example.test1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.databinding.HeaderFavoriteBinding
import com.example.test1.databinding.HeaderRcBinding
import com.example.test1.databinding.ItemArticleBinding

class AppHolderFactory{
    companion object{
        fun getHolder(parent: ViewGroup, viewType: Int, liked: NewsViewHolder.Liked): RecyclerView.ViewHolder {
            return when (viewType) {
                HolderView.HEADER -> {
                    val binding = HeaderRcBinding.inflate(LayoutInflater.from(parent.context),parent, false )
                    HeaderViewHolder(binding)
                }
                HolderView.HEADER_Favorite-> {
                    val binding = HeaderFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent, false )
                    HeaderFavrite(binding)
                }
                else->{
                    val binding= ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                    NewsViewHolder(binding, liked)
                }
            }
        }
    }
}