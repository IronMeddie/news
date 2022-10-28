package com.example.test1.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.R
import com.example.test1.databinding.ItemArticleBinding
import com.example.test1.models.Article

class NewsViewHolder(binding: ItemArticleBinding, private val liked: Liked) : RecyclerView.ViewHolder(binding.root) {
    val mb = binding
    fun bind(article: Article?) {
        mb.apply {
             Glide.with(articleImage).load(article?.urlToImage)
                .placeholder(R.drawable.ic_news_default).dontAnimate()
                .into(articleImage)
            articleImage.clipToOutline = true
            articleTitle.text = article?.title
            articleDate.text = article?.publishedAt
            articleFavorite.setOnClickListener {
                    if (article != null) {
                        liked.onClickLiked(article)
                    }
            }
            if (article?.liked == true) articleFavorite.setImageResource(R.drawable.ic_favorited)
            else articleFavorite.setImageResource(R.drawable.ic_favorite)
        }
    }

    interface Liked{
        fun onClickLiked(article: Article){
        }
    }


}

