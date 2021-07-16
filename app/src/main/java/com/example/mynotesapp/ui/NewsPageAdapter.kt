package com.example.mynotesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.NewsItemBinding
import com.example.mynotesapp.network.Articles

class NewsPageAdapter :
    RecyclerView.Adapter<NewsPageAdapter.NewsPageViewHolder>() {

    private var newsList: ArrayList<Articles> = ArrayList()

    class NewsPageViewHolder constructor(itemView: NewsItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        val itemBinding: NewsItemBinding = itemView

        fun bind(articles: Articles) {
            itemBinding.news = articles
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPageViewHolder {
        val view: NewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_item,
            parent,
            false
        )
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