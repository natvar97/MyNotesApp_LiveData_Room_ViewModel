package com.example.mynotesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynotesapp.R
import com.example.mynotesapp.network.Articles

class NewsPageAdapter :
    RecyclerView.Adapter<NewsPageAdapter.NewsPageViewHolder>() {

    private var newsList: ArrayList<Articles> = ArrayList()

    class NewsPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor = itemView.findViewById<TextView>(R.id.tv_author)
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        val tvUrlNews = itemView.findViewById<TextView>(R.id.tv_url_news)
        val ivImageNews = itemView.findViewById<ImageView>(R.id.iv_image_news)
        val tvPublishedDate = itemView.findViewById<TextView>(R.id.tv_published_date)
        val tvContent = itemView.findViewById<TextView>(R.id.tv_content)

        fun bind(articles: Articles) {
            tvAuthor.text = articles.author
            tvTitle.text = articles.title
            tvDescription.text = articles.description
            tvUrlNews.text = articles.url
            tvPublishedDate.text = articles.publishedAt
            tvContent.text = articles.content
            Glide.with(itemView.context)
                .load(articles.urlToImage)
                .into(ivImageNews)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
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
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}