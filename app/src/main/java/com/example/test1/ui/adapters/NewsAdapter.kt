package com.example.test1.ui.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test1.models.Article
import com.example.test1.models.Source
import com.example.test1.ui.adapters.HolderView.Companion.CONTENT
import com.example.test1.ui.adapters.HolderView.Companion.HEADER


class NewsAdapter : RecyclerView.Adapter<ViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url && oldItem.title == newItem.title
        }

    }

    private val differ = AsyncListDiffer(this, callback)


    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position] == emptyArticle()) HEADER else CONTENT
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) = AppHolderFactory.getHolder(parent = viewGroup , viewType = i)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder is NewsViewHolder){
            val article = differ.currentList[position]
            holder.bind(article)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((Article) -> Unit)? = null

    fun onItemClikListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


    fun updateListAddHeader(list: MutableList<Article>) {
        list[0] = emptyArticle()
        differ.submitList(list)
    }

    fun updateList(list: List<Article>) {
        differ.submitList(list)
    }

    private fun emptyArticle(): Article =  Article(
        null,
        "null",
        "Header_125987_MaiN__FragMeNt",
        "null",
        "null",
        Source("", ""),
        "null",
        "Header_125987_MaiN__FragMeNt",
        "null"
    ) // header?





}

