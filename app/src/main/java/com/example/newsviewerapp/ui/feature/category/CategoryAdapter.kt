package com.example.newsviewerapp.ui.feature.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Article
import com.example.newsviewerapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val itemClickListener: (Article) -> Unit
) : PagingDataAdapter<Article, CategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article, itemClickListener: (Article) -> Unit) {
            binding.categoryNews = item
            binding.root.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it, itemClickListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
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