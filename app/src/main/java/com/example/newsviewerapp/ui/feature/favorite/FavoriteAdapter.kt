package com.example.newsviewerapp.ui.feature.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Article
import com.example.newsviewerapp.databinding.ItemFavoriteBinding

class FavoriteAdapter() : ListAdapter<Article, FavoriteAdapter.FavoriteViewHolder>(DIFF_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Article
        ) {
            binding.article = item
        }
    }

    companion object {

        private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }
}