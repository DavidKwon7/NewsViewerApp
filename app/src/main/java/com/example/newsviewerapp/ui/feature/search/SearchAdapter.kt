package com.example.newsviewerapp.ui.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Article
import com.example.newsviewerapp.databinding.ItemHomeBinding

class SearchAdapter(
    private val itemClickListener: (Article) -> Unit
): PagingDataAdapter<Article, SearchAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    inner class HomeViewHolder(
        private val binding: ItemHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article, itemClickListener: (Article) -> Unit) {
            binding.searchNews = item
            binding.root.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it, itemClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}