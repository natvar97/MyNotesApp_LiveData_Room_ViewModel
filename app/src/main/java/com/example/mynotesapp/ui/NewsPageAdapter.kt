package com.example.mynotesapp.ui

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynotesapp.databinding.NewsItemBinding
import com.example.mynotesapp.network.Articles

class NewsPageAdapter :
    RecyclerView.Adapter<NewsPageAdapter.NewsPageViewHolder>() {

    private var newsList: ArrayList<Articles> = ArrayList()

    class NewsPageViewHolder(itemView: NewsItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val tvAuthor = itemView.tvAuthor
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
        val tvUrlNews = itemView.tvUrlNews
        val ivImageNews = itemView.ivImageNews
        val tvPublishedDate = itemView.tvPublishedDate
        val tvContent = itemView.tvContent

        fun bind(articles: Articles) {
            tvAuthor.text = articles.author
            tvTitle.text = articles.title
            tvDescription.text = articles.description
            tvUrlNews.movementMethod = LinkMovementMethod.getInstance()
            tvUrlNews.text = Html.fromHtml(articles.url)
            tvPublishedDate.text = articles.publishedAt
            tvContent.text = articles.content
            Glide.with(itemView.context)
                .load(articles.urlToImage)
                .into(ivImageNews)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPageViewHolder {
        val view = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsPageViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsPageViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setData(list: List<Articles>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}