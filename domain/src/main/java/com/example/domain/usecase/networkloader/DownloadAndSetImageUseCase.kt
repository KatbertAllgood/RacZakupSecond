package com.example.domain.usecase.networkloader

import android.widget.ImageView
import com.example.domain.repository.NetworkLoaderRepository

class DownloadAndSetImageUseCase(
    private val networkLoaderRepository: NetworkLoaderRepository
) {
    fun invoke(
        url: String,
        imageView: ImageView
    ) = networkLoaderRepository.downloadAndSetImage(
        url,
        imageView
    )
}