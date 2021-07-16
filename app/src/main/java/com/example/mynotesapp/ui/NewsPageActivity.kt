package com.example.mynotesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.NewsPageLayoutBinding
import com.example.mynotesapp.viewmodel.NewsViewModel

class NewsPageActivity : AppCompatActivity() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsPageAdapter: NewsPageAdapter
    private lateinit var mBinding: NewsPageLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.news_page_layout)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsRecyclerView = findViewById(R.id.newsRecyclerView)
        newsPageAdapter = NewsPageAdapter()
        newsViewModel.getNews()

        mBinding.srlNotes.setOnRefreshListener {
            mBinding.srlNotes.isRefreshing = true
            newsViewModel.getNews()
            mBinding.srlNotes.isRefreshing = false
        }

        newsViewModel.getAllNews.observe(this, Observer {
            newsPageAdapter.setData(it)
        })
        mBinding.newsAdapter = newsPageAdapter

    }
}