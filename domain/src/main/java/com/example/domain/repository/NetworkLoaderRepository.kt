package com.example.domain.repository

import android.widget.ImageView

interface NetworkLoaderRepository {

    fun downloadAndSetImage(url: String, imageView: ImageView)

}