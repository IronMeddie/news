package com.example.test1.ui.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test1.models.Article
import com.example.test1.ui.adapters.HolderView.Companion.CONTENT
import com.example.test1.ui.adapters.HolderView.Companion.HEADER
import com.example.test1.ui.adapters.HolderView.Companion.HEADER_Favorite


class NewsAdapter(private val liked: NewsViewHolder.Liked) : RecyclerView.Adapter<ViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url && oldItem.liked==newItem.liked
        }

    }

    private val differ = AsyncListDiffer(this, callback)


    override fun getItemViewType(position: Int): Int {
        return when(differ.currentList[position]){
            emptyArticle(HEADER) -> HEADER
            emptyArticle(HEADER_Favorite) -> HEADER_Favorite
            else -> CONTENT
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) = AppHolderFactory.getHolder(parent = viewGroup , viewType = i , liked)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is NewsViewHolder){
            val article = differ.currentList[position]
            holder.bind(article)
            holder.itemView.setOnClickListener {
               onItemClickListener?.let {
                   it(article)
               }
           }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setonItemClikListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


    fun updateListAddHeader(list: MutableList<Article>, header: Int) {
        list[0] = emptyArticle(header)
        differ.submitList(list)
    }

    fun updateList(list: List<Article>) {
        differ.submitList(list)
    }

    private fun emptyArticle(header: Int): Article =  Article(
        0,
        "null",
        "Header_$header",
        "null",
        "null",
//        Source("", ""),
        "null",
        "Header_$header",
        "null"
    ) // header?




    fun ItemLiked(article: Article){
        val position = differ.currentList.indexOf(article)
        article.liked = !article.liked
        notifyItemChanged(position)
    }

    fun itemRemoved(article: Article){
        val list = differ.currentList.toMutableList()
        article.liked = !article.liked
        list.remove(article)
        differ.submitList(list)
    }



}

