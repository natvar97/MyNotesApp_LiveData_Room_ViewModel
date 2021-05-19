package com.example.mynotesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynotesapp.Constants
import com.example.mynotesapp.network.Articles
import com.example.mynotesapp.network.NewsApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsViewModel : ViewModel() {

    private val newsApiService = NewsApiService()
    private val compositeDisposable = CompositeDisposable()

    var getAllNews = MutableLiveData<List<Articles>>()

    var observable: Observable<MutableList<Articles>> = newsApiService.getNews()
        .map { result ->
            Observable.fromIterable(result.articles)
        }
        .flatMap { list -> list }
        .filter { query ->
            query.source!!.name = Constants.SOURCES
            return@filter true
        }
        .toList().toObservable()


    fun getNews() {
        observable
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : Observer<List<Articles>> {
                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: List<Articles>?) {
                    getAllNews.value = t!!
                }

                override fun onError(e: Throwable?) {

                }

                override fun onComplete() {

                }

            })

    }

    /*
        fun getNews() {
            compositeDisposable.add(
                newsApiService.getNews()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<List<Articles>>() {
                        override fun onSuccess(t: List<Articles>?) {
                            getAllNews.value = t!!
                            Log.e("tag" , "${ t[0   ].author }")
                        }

                        override fun onError(e: Throwable?) {
                            Log.e("tag", "${e!!.message}")
                        }

                    })
            )

        }
    */
}