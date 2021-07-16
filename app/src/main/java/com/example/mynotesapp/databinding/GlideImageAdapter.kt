package com.example.mynotesapp.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mynotesapp.R

object GlideImageAdapter {

    @JvmStatic
    @BindingAdapter("imageSource")
    fun setImage(view: ImageView, url: String?) {
        val context = view.context

        val option = RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)

        Glide.with(context)
            .setDefaultRequestOptions(option)
            .load(url)
            .into(view)
    }

}