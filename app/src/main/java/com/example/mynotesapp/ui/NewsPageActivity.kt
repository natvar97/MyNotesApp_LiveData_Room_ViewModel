package com.example.mynotesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.R
import com.example.mynotesapp.network.Articles
import com.example.mynotesapp.viewmodel.NewsViewModel

class NewsPageActivity : AppCompatActivity() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsPageAdapter: NewsPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_page_layout)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsRecyclerView = findViewById(R.id.newsRecyclerView)
        newsPageAdapter = NewsPageAdapter()
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.adapter = newsPageAdapter
        newsViewModel.getNews()

        newsViewModel.getAllNews.observe(this, Observer {
            newsPageAdapter.setData(it)
        })

    }
}