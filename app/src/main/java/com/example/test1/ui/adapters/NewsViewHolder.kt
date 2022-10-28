package com.example.test1.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.databinding.ItemArticleBinding
import com.example.test1.models.Article

class NewsViewHolder(binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
    val mb = binding
    fun bind(article: Article?) {
        mb.apply {
             Glide.with(articleImage).load(article?.urlToImage)
                .placeholder(com.example.test1.R.drawable.ic_news_default).dontAnimate()
                .into(articleImage)
            articleImage.clipToOutline = true
            articleTitle.text = article?.title
            articleDate.text = article?.publishedAt
        }

    }
}