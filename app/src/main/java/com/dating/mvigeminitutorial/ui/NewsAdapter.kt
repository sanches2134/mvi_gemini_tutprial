package com.dating.mvigeminitutorial.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.dating.mvigeminitutorial.R
import com.dating.mvigeminitutorial.databinding.ItemPreviewBinding
import com.dating.mvigeminitutorial.domain.entity.Article

class NewsAdapter(
    private val action: (Int) -> Unit

) : androidx.recyclerview.widget.ListAdapter<Article, NewsViewHolder>(object :
    DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder.create(parent, action)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NewsViewHolder(
    view: View, private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val binding: ItemPreviewBinding by viewBinding()

    fun bind(article: Article) {

        with(binding) {
            Glide.with(binding.root.context).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            tvTitle.setOnClickListener {
                action(1)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, action: (Int) -> Unit) =
            NewsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_preview, parent, false),
                action
            )
    }
}
