package com.example.data.repository

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.domain.repository.NetworkLoaderRepository

class NetworkLoaderRepositoryImpl(
    private val context: Context
) : NetworkLoaderRepository {


    override fun downloadAndSetImage(url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

}