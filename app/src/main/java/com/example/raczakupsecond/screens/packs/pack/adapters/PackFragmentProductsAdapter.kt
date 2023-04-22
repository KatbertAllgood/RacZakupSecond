package com.example.raczakupsecond.screens.packs.pack.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.repository.NetworkLoaderRepository
import com.example.domain.usecase.networkloader.DownloadAndSetImageUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.ItemProductHealthySetBinding
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

class PackFragmentProductsAdapter(
    private val productsList: List<ProductParamsDomain>
) : RecyclerView.Adapter<PackFragmentProductsAdapter.ProductHolder>() {

    private val networkLoaderRepository = App.getNetworkLoaderRepositoty()
    private val downloadAndSetImageUseCase = DownloadAndSetImageUseCase(networkLoaderRepository)

    var onItemClick : ((ProductParamsDomain) -> Unit)? = null

    inner class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemProductHealthySetBinding.bind(item)

        fun bind(product: ProductParamsDomain) = with(binding) {

            binding.itemProductHealthySetTitle.text = product.title

            downloadAndSetImageUseCase.invoke(product.image, binding.itemProductHealthySetImage)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product_healthy_set,
                parent,
                false
            )
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productsList[position])

        val product = productsList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
            notifyDataSetChanged()
        }

    }

}