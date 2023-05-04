package com.example.raczakupsecond.screens.shop.productcard

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.usecase.networkloader.DownloadAndSetImageUseCase
import com.example.raczakupsecond.app.App

class ProductCardFragmentVM : ViewModel() {

    private val networkLoaderRepository = App.getNetworkLoaderRepository()
    private val downloadAndSetImageUseCase = DownloadAndSetImageUseCase(networkLoaderRepository)

    private val productParamsLiveData = MutableLiveData<ProductParamsDomain>()
    fun getProductParamsLiveData(): LiveData<ProductParamsDomain> = productParamsLiveData
    fun setProductParamsLiveData(params: ProductParamsDomain) {
        productParamsLiveData.value = params
    }

    fun downloadAndSetImage (
        url: String,
        imageView: ImageView
    ) = downloadAndSetImageUseCase.invoke(url, imageView)
}