package com.example.test1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.databinding.HeaderRcBinding
import com.example.test1.databinding.ItemArticleBinding

class AppHolderFactory{
    companion object{
        fun getHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                HolderView.HEADER -> {
                    val binding = HeaderRcBinding.inflate(LayoutInflater.from(parent.context),parent, false )
                    HeaderViewHolder(binding)
                }
                else->{
                    val binding= ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                    NewsViewHolder(binding)
                }
            }
        }
    }
}