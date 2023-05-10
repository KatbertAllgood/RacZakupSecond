package com.example.raczakupsecond.screens.packs.pack.defaultpack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.usecase.networkloader.DownloadAndSetImageUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.ItemProductHealthySetBinding

class PackFragmentProductsAdapter(
    private val productsList: List<ProductParamsDomain>
) : RecyclerView.Adapter<PackFragmentProductsAdapter.ProductHolder>() {

    private val networkLoaderRepository = App.getNetworkLoaderRepository()
    private val downloadAndSetImageUseCase = DownloadAndSetImageUseCase(networkLoaderRepository)

    var onItemClick : ((ProductParamsDomain) -> Unit)? = null

    inner class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemProductHealthySetBinding.bind(item)

        fun bind(product: ProductParamsDomain) = with(binding) {

            downloadAndSetImageUseCase.invoke(product.image, itemProductHealthySetImage)

            itemProductHealthySetTitle.text = product.title
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