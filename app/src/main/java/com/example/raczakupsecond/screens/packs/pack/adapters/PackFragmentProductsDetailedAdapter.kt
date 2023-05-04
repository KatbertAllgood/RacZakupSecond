package com.example.raczakupsecond.screens.packs.pack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.usecase.networkloader.DownloadAndSetImageUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.ItemProductHealthySetDetailedBinding

class PackFragmentProductsDetailedAdapter(
    private val productsList: List<ProductParamsDomain>
) : RecyclerView.Adapter<PackFragmentProductsDetailedAdapter.ProductHolder>() {

    private val networkLoaderRepository = App.getNetworkLoaderRepository()
    private val downloadAndSetImageUseCase = DownloadAndSetImageUseCase(networkLoaderRepository)

    var onItemClick : ((ProductParamsDomain) -> Unit)? = null

    inner class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemProductHealthySetDetailedBinding.bind(item)

        fun bind(product: ProductParamsDomain) = with(binding) {

            listOf(
                itemProductHealthySetDetailedCarbohydratesProgress,
                itemProductHealthySetDetailedFatsProgress,
                itemProductHealthySetDetailedProteinsProgress
            ).forEach {
                var max = 0

                for (i in listOf(product.carbohydrates, product.fats, product.proteins)) {
                    if (i > max) {
                        max = i.toInt()
                    }
                }

//                it.max = product.weigh.toInt()
                it.max = max
            }

            downloadAndSetImageUseCase.invoke(product.image, itemProductHealthySetDetailedImage)

            itemProductHealthySetDetailedTitle.text = product.title

            itemProductHealthySetDetailedCarbohydratesProgress.progress = product.carbohydrates.toInt()
            itemProductHealthySetDetailedFatsProgress.progress = product.fats.toInt()
            itemProductHealthySetDetailedProteinsProgress.progress = product.proteins.toInt()

            itemProductHealthySetDetailedFatsValue.text = "${product.fats.toString()} г"
            itemProductHealthySetDetailedCarbohydratesValue.text = "${product.carbohydrates.toString()} г"
            itemProductHealthySetDetailedProteinsValue.text = "${product.proteins.toString()} г"

            itemProductHealthySetDetailedPriceValue.text = "${product.price.toString()} ₽"

            itemProductHealthySetDetailedKcalValue.text = product.energyValue.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product_healthy_set_detailed,
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